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

public class SelectTech extends SBTS.Control{
    protected DBI dbi;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    // To check if there is a session
    HttpSession session = request.getSession(true);
    // Inserts the shared class into the bean
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    // Assigns the status and bookID when Status is passed into request.getParameter
    String taskstatus = request.getParameter("Status");
    String setbookID = bean.getBookID();
    
    //To check if there is a value when AssignTask is passed 
    if(request.getParameter("AssignTask") != null){
    //To get the designer, Editor and Admin after the bean is passed
    getDesigners(bean);
    getEditors(bean);
    getAdmins(bean);
    //To retrieve the taskstatus and bookID in the bean
    bean.setChooseTaskStatus(taskstatus);
    bean.setBookID(setbookID);
    gotoPage("/SelectTech.jsp", request, response); //Takes you to the SelectTech.jsp page
    }
    
    //Fetches the designer, editor, admin, taskstatus and bookID in the bean, if there is a value in Parallel task
    else if(request.getParameter("ParallelTask") != null){
    getDesigners(bean);
    getEditors(bean);
    getAdmins(bean);
    bean.setChooseTaskStatus(taskstatus);
    bean.setBookID(setbookID);
    gotoPage("/SelectTechParallel.jsp", request, response); //Takes you to the SelectTechparallel.jsp page
    }
    
    }
    
    //method to get the designers
    private void getDesigners(SBTS.Shared bean) throws ServletException, IOException{
        String[][] designers;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        // To check if there is a connection to the dbi 
        if(dbi.connect()){
        //Take the designer value from dbi to the bean
        designers= dbi.getDesigners();
        bean.setDesigners(designers);
        
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

//Method to get the editors
private void getEditors(SBTS.Shared bean) throws ServletException, IOException{
        String[][] editors;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //To check if there is a connection to the dbi
        if(dbi.connect()){
        // Takes the editor value from dbi to the bean
        editors= dbi.getEditors();
        bean.setEditors(editors);
        
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

//Method to get admin
private void getAdmins(SBTS.Shared bean) throws ServletException, IOException{
        String[][] admins;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //To check if there is a connection to the dbi
        if(dbi.connect()){
        // Takes the admin value from dbi to the bean
        admins= dbi.getAdmins();
        bean.setAdmins(admins);
        
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
 
