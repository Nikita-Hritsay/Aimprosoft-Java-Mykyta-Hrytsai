<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 01.10.21
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add department</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a href="displayAllDepartments">Home page</a>
    <a href="displayEmployees">All Employees</a>
    <a href="createOrUpdateEmployeeForm">Add Employee</a>
    <a href="createOrUpdateDepartmentForm">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">
    <form action="addDepartmentServlet" method="post">
        Please enter your name of department:  <input type="text" name="name" class="input_param" size="15px" value="${department.name}"> <br>
        <p class="error_massage">${errors.get("name")}</p>
        Please enter your address of department:  <input type="text" name="address" class="input_param" size="15px" value="${department.address}"> <br>
        <p class="error_massage">${errors.get("address")}</p>
        <input type="submit" value="submit">
    </form>
</div>


</body>
</html>
