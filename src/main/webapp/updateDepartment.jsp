<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 04.10.21
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aimprosoft Java</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a href="/aimlearning_war_exploded/">Home page</a>
    <a href="/aimlearning_war_exploded/allEmployees.jsp">All Employees</a>
    <a href="/aimlearning_war_exploded/addEmployee.jsp">Add Employee</a>
    <a href="/aimlearning_war_exploded/addDepartment.jsp">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">

    <%
        if(request.getParameter("wrong") != null){
            PrintWriter printWriter = response.getWriter();
            printWriter.println(request.getParameter("wrong"));
        }
    %>


    <form action="updateDepartmentServlet" method="post">
        <p>Department id: <%= request.getParameter("id") %></p>
        <input name="id" class="input_param_id" value="<%= request.getParameter("id") %>" type="hidden">
        Please enter department name:  <input type="text" name="name" class="input_param" size="15px" value="<%= request.getParameter("name") %>"> <br>
        Please enter your last name:  <input type="text" name="address" class="input_param" size="15px" value="<%= request.getParameter("address") %>"> <br>
        <input type="submit" value="submit">
    </form>
</div>



</body>
</html>
