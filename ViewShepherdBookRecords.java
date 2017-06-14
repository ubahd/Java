/******************************************************************************************

ViewShepherdBookRecords.java

The purpose of this servlet is to get the list of books from the database that belong to a shepherd and need their help.

   + This servlet is invoked by MainPage.jsp
   + This servlet dispatches to ViewShepherdBookRecords.jsp
   
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

public class ViewShepherdBookRecords extends SBTS.Control{
    protected DBI dbi;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Get the id of the employee who is logged in
	int empid = bean.getEmpId();
   
   //Call the method that gets the lists of the books belonging to the shepherd
    getShepherdBookList(bean, empid);
    gotoPage("/ViewShepherdBookRecords.jsp", request, response); //Dispatch to ViewShepherdBookRecords.jsp to show the book list
    
    }
    
    //Method to get the list of books belonging to a shepherd
private void getShepherdBookList(SBTS.Shared bean, int empid) throws ServletException, IOException{
	// create a DBI shell
        SBTS.DBI dbi = null;
	// make a matrix to hold the book list of data
	String[][] Vsbooks;
	//int empid = bean.getEmpId();

	
try{
    // create an instance of the DBI
	 dbi = new SBTS.DBI();

        //Check if there is a database connection to Tomcat
	if(dbi.connect()){
        
        // Get the list of books from the DB for this shepherd that needs the shepherd and hold them in a matrix  
        Vsbooks= dbi.getShepherdBookList(empid);

	// save this list of books in the bean
        bean.setShepherdBookList(Vsbooks);
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
 
