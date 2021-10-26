<%--@elvariable id="department" type="java.util.Map"--%>
<%--@elvariable id="errors" type="com"--%>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 08.10.21
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a class="header_ref" href="displayAllDepartments">Home page</a>
    <a class="header_ref" href="displayEmployees">All Employees</a>
    <a class="header_ref" href="createOrUpdateEmployeeForm">Add Employee</a>
    <a class="header_ref" href="createOrUpdateDepartmentForm">Add Department</a>
</div>

<div style="margin-top: 50px;" class="createOrUpdateForm">
    <form action="createOrUpdateDepartment" method="post" >
        <input name="id" class="input_param_id" value="${department.idDepartment}" type="hidden">
        Please enter name of department: <label>
        <input type="text" name="name" class="input_param" size="15px"
                                                     value="${department.name}">
            </label>
        <br>
        <p class="error_massage">${errors.name}</p>
        Please enter address of department: <label>
        <input type="text" name="address" class="input_param" size="15px"
                                                        value="${department.address}">
        </label>
        <br>
        <p class="error_massage">${errors.address}</p>
        <input type="submit" class="submit_createOrUpdate" value="submit">
    </form>
</div>

</body>
</html>
