/******************************************************************************************

ViewTaskDetails2.java

The purpose of this servlet is to get the tasks that is assigned to a specific book.

   + This servlet is invoked by ??
   + This servlet dispatches to ??
   
******************************************************************************************/

package SBTS;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import SBTS.DBI;
import SBTS.Control;
import SBTS.Shared;

public class ViewTaskDetails2 extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
    HttpSession session = request.getSession(true);
    
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    
    String [][] SelectedBook = bean.getTechTaskList();
  
//Get the taskID from the getTechTaskList and saving it in the bean
    int BookID = Integer.parseInt(request.getParameter("BookID"));
    String setbookID = SelectedBook[BookID][0];
    bean.setBookID(setbookID); 
    bean.setSelectedBook(SelectedBook);
    getTaskDetails(bean, setbookID);

//Gets the bookID from the getTechTaskList and saving
	int BookID2 = Integer.parseInt(request.getParameter("BookID"));
    String setbookID2 = SelectedBook[BookID2][1];
    bean.setBookID2(setbookID2); 



    gotoPage("/ViewTaskDetails2.jsp", request, response); 
    
    }
   
private void getTaskDetails(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        String[][] Vtaskdetails;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
       
        if(dbi.connect()){
        Vtaskdetails = dbi.getTaskDetails(BookID);
        bean.setTaskDetails(Vtaskdetails);
        } 
}

catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}

finally{
      dbi.close();
}
}
}//End of Class 
 
