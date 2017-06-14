<!-- ?? dispatches to this jsp -->
<!-- This file ??  -->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- Insert Logo -->
    <h1 align = "center"><img  align = "center" src= "images/orilogo.png" alt = "Book Logo" style= "width: 200px; height: 200px"></h1> 
</head>
<body style="background: url(images/Color.jpg) no-repeat; background-size: cover;">
                        <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                        <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                        <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                        <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!-- -->

<!-- Display the option to continue from this screen to the user  -->

<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<a href="ViewTechTaskList.jsp"><button type="button" style="float:right;">Back</button></a>

<br><h2 align ="center"> Tasks Details</h2>
<%
// Get the task details from the bean
String [][] taskdetails = shared.getTaskDetails();
if(taskdetails != null && taskdetails.length != 0){
%>
<!-- Creating table  -->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
     	 <th>Task ID</th>
	 <th>Book ID</th>
	 <th>Task Type</th>
	 <th>Task Notes</th>
	 <th>Task Status </th>
	 <th>TechnicianID</th>
	 <th>Technician First Name</th>
	 <th>Technician Last Name</th>
</tr>
<%
int count = 0;
//
for(String[] details : taskdetails){
%>
<tr>
<!-- Fields of the task details  -->
<td name="t.TaskID" align="center"><%=details[0]%></td>
<td name="t.BookID" align="center"><%=details[1]%></td>
<td name="t.TaskType" align="center"><%=details[2]%></td>
<td name="t.TaskNotes" align="center"><%=details[3]%></td>
<td name="t.TaskStatus" align="center"><%=details[4]%></td>
<td name="t.TechnicianID" align="center"><%=details[5]%></td>
<td name="e.EmpFirstName" align="center"><%=details[6]%></td>
<td name="e.EmpLastName" align="center"><%=details[7]%></td>
</tr>
<%
count++;
}//end of for loop
%>
</table>
<%
}//end of if
else
{
%>
<p>There are no tasks available.</p>
<%
}//end of else
%>

<h2 align = "center">Assign a status for the task</h2>

<!--   -->
<form id = "Update" name="Update" method ="Post" action="Update" align = "center">
<select id = "Status" name ="Status">
<option value="Task Hold" id="Task Hold">Task Hold</option>
<option value="Task Complete" id="Task Complete">Task Complete</option>
<option value="Task Problem" id="Task Problem">Task Problem</option>
</select><br>

Task Notes: <input type="text" style="width: 300px;" id="Tasknotes" name="Tasknotes" value="">
<input type="submit" name="Update" value="Update Task status"> <!--  -->
</form>

</body>


</html>
