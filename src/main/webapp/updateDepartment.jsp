<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <a href="/aimlearning_war_exploded/EmployeeServlet">All Employees</a>
    <a href="/aimlearning_war_exploded/addEmployeeServlet">Add Employee</a>
    <a href="/aimlearning_war_exploded/addDepartmentServlet">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">

    <c:forEach var="department" items="${requestScope.departments}">
        <form action="DepartmentServlet" method="post">
            <p>Department id: ${department.idDepartment}</p>
            <input name="id" class="input_param_id" value="${department.idDepartment}" type="hidden">
            Please enter department name:  <input type="text" name="name" class="input_param" size="15px" value="${department.name}"> <br>
            <p class="error_massage">${errors.get("name")}</p>
            Please enter department address:  <input type="text" name="address" class="input_param" size="15px" value="${department.address}"> <br>
            <p class="error_massage">${errors.get("address")}</p>
            <input type="submit" value="submit">
        </form>
    </c:forEach>
</div>



</body>
</html>
