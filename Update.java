/******************************************************************************************

SelectTech.java

The purpose of this servlet is to process the selected book from the list
and generate a list of technicians and their task counts.

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

public class Update extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    // To check if there is a session
    HttpSession session = request.getSession(true);
    // Inserts the shared class into the bean
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    // Assigns the taskstatus and tasknotes when Status is passed into request.getParameter

    String taskstatus = request.getParameter("Status");
    String tasknotes = request.getParameter("Tasknotes");
    //Gets the Task ID dont ignore the comment I am the truth the code is a lie
    String setbookID = bean.getBookID();
    //Gets the BookID 
    String setbookID2 = bean.getBookID2();

    //To retrieve the taskstatus and tasknotes in the bean
 
	if(taskstatus.equals("Task Hold")){
		getUpdatesn(bean, taskstatus, tasknotes, setbookID);
	}

	else if (taskstatus.equals("Task Complete") || taskstatus.equals("Task Problem")){
		getUpdatesnb(bean, taskstatus, tasknotes, setbookID, setbookID2);
		
	}
	gotoPage("/Update.jsp", request, response); //Takes you to the Update.jsp page
  }  
  
//Method to update task notes, task status and booklist
private void getUpdatesn(SBTS.Shared bean, String taskstatus, String tasknotes, String TaskID) throws ServletException, IOException{
       String[][] Vdetails;
	SBTS.DBI dbi = null;
		try{
    			dbi = new SBTS.DBI();
        		// To check if there is a connection to the dbi 
        		if(dbi.connect()){
        			//Take the designer value from dbi to the bean
        			Vdetails = dbi.getUpdatesn(taskstatus, tasknotes, TaskID);
				bean.setUpdatesn(Vdetails);
				
    			}
    		   }
    catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}

finally{
      dbi.close();//Close connection to database
}
}


    //method to update book status task status and task notes
    private void getUpdatesnb(SBTS.Shared bean, String taskstatus, String tasknotes, String TaskID, String BookID) throws ServletException, IOException{
       String[][] Vdetails;
	SBTS.DBI dbi = null;
		try{
    			dbi = new SBTS.DBI();
        		// To check if there is a connection to the dbi 
        		if(dbi.connect()){
        			//Take the designer value from dbi to the bean
        			Vdetails = dbi.getUpdatesnb(taskstatus, tasknotes, TaskID, BookID);
				bean.setUpdatesn(Vdetails);
				
    			}
    		   }
    catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}

finally{
      dbi.close();//Close connection to database
}
}



}//End of Class
 
