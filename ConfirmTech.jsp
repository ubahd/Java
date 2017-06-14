<!-- -->
<!-- This -->
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



<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!-- -->

<!-- Display options to the user -->

<a href="ViewShepherdBookRecords"><button type="button" style="float:right;">Back to Shepherd Book List</button></a>
<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<p>Task has been assigned!</p>

<%
// 
String [][] confirmtask = shared.getConfirmTask();
if(confirmtask != null && confirmtask.length != 0){
%>
<!--  -->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!--  -->
     <th>Task ID</th>
     <th>Task Type</th>
     <th>Task Status</th>
     <th>Start Date</th>
     <th>Technician First Name</th>
	<th>Technician Last Name</th>
</tr>
<%
int count = 0;
// 
for(String[] details : confirmtask){
%>
<tr>
<!--   -->
<td name="t.TaskID" align="center"><%=details[0]%></td>
<td name="t.TaskType" align="center"><%=details[2]%></td>
<td name="t.TaskStatus" align="center"><%=details[3]%></td>
<td name="t.StartDate" align="center"><%=details[1]%></td>
<td name="e.EmpFirstName" align="center"><%=details[4]%></td>
<td name="e.EmpLastName" align="center"><%=details[5]%></td> 
</tr>
<%
count++;
}//end of for loop
%>
</table>
<%
} // End of if
else
{
%>
<p>There are no tasks available.</p>
<%
} //End of else
%>

</body>
<html>
