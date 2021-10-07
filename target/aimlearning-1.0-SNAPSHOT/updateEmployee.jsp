<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.aimprosoft.aimlearning.model.Department" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 01.10.21
  Time: 09:56
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

    <C:forEach items="${requestScope.employees}" var="employee">
        <form action="EmployeeServlet" method="post">
            <%--@declare id="iddepartments"--%><p>User id: ${employee.id}</p>
            <input type="hidden" name="id" class="input_param" size="20px" value="${employee.id}">
            Please enter your first name:  <input type="text" name="firstName" class="input_param" size="15px" value="${employee.firstName}"> <br>
            Please enter your last name:  <input type="text" name="lastName" class="input_param" size="15px" value="${employee.lastName}"> <br>
            Please enter your email:  <input type="text" name="email" class="input_param" size="15px" value="${employee.email}"> <br>
            Please enter your salary:  <input type="number" name="salary" class="input_param" size="15px" value="${employee.salary}"> <br><br>
            Please enter your hire date:  <input type="date" name="hireDate" class="input_param" size="15px" value="${employee.hireDate}"> <br><br>
            Please enter your department id:  <input list="idDepartments" name="iddepartment" class="input_param" size="15px" value="${employee.idDepartment}"> <br><br><br>
            <input type="submit" value="submit">
        </form>
    </C:forEach>

</div>


</body>
</html>
