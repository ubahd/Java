<!-- ViewShepherdBookRecords servlet dispatches to this jsp -->
<!-- This file displays the list of books that belong to a shepherd-->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- Insert SBTS Logo-->
    <h1 align = "center"><img  align = "center" src= "images/orilogo.png" alt = "Book Logo" style= "width: 200px; height: 200px"></h1> 
</head>
<body style="background: url(images/Color.jpg) no-repeat; background-size: cover;">
                        <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                        <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                        <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                        <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!--Get the firstname of the employee that is logged in and display it-->

<!-- Display options to continue from this screen to the user -->

<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<br><h2 align ="center">View Task List</h2>
<%
//Get the list of task that were set in the bean
String [][] books = shared.getTechTaskList();
// if the books list is not empty
if(books != null && books.length != 0  ){
%>
<!-- Table labels used to display the book information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
     	 <th>Task ID</th>
	 <th>BookID</th>
	 <th>Task Type</th>
	 <th>Task Notes</th>
	 <th>Task Status</th>
 	 <th>Tech ID</th>

</tr>
<%
int count = 0;
//Create an array for the books
for(String[] book : books){
%>
<tr>
<!-- Display data fields from book array that correlates to the columns used in the query -->
<td name="Task ID" align="center"><%=book[0]%></td>
<td name="BookID" align="center"><%=book[1]%></td>
<td name="Task Type" align="center"><%=book[2]%></td>
<td name="Task Notes" align="center"><%=book[3]%></td>
<td name="Task Status" align="center"><%=book[4]%></td>
<td name="Tech ID" align="center"><%=book[5]%></td>
				<td name = "BookID" align="center"> 
				<form id="selectBook<%=count%>" method="POST" action="ViewTaskDetails2"> <!-- each book has its own form -->
				<input type="submit" name="Submit" value="Select"/> <!-- submit book selected to ViewTaskDetails servlet-->
				<input type="hidden" name="BookID" value="<%=count%>" /> <!-- send row index of the selected book -->
				</form>
</td>
</tr>
<%
count++; // next row
}//end of for loop
%>
</table>
<%
} //end of if
else // the books list matrix was empty
{
%>
<p>There are no task assigned to you.</p>
<%
} //end of else
%>

</body>


</html>
