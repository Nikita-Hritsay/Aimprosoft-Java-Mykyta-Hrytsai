<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 08.10.21
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a href="displayAllDepartments">Home page</a>
    <a href="/EmployeeServlet">All Employees</a>
    <a href="/addEmployeeServlet">Add Employee</a>
    <a href="createOrUpdateDepartmentForm">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">
    <div align="center" style="margin-top: 50px;">
        <form action="createOrUpdateDepartment" method="post">
            <input name="id" class="input_param_id" value="${department.idDepartment}" type="hidden">
            Please enter your name of department:  <input type="text" name="name" class="input_param" size="15px" value="${department.name}"> <br>
            <p class="error_massage">${errors.get("name")}</p>
            Please enter your address of department:  <input type="text" name="address" class="input_param" size="15px" value="${department.address}"> <br>
            <p class="error_massage">${errors.get("address")}</p>
            <input type="submit" value="submit">
        </form>

    </div>
</div>

</body>
</html>
