<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.aimprosoft.aimlearning.model.Employee" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.aimprosoft.aimlearning.model.Department" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAO" %>
<%@ page import="java.sql.PreparedStatement" %>
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
    <c:if test="${empty employees}">
        <p>Empty</p>
    </c:if>
    <c:if test="${!empty requestScope.employees}">
        <table border="1">
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Salary</th>
                <th>Hire Date</th>
                <th>Department id</th>
                <th>Action</th>
            </tr>
            <c:forEach var="employee" items="${requestScope.employees}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.email}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.hireDate}</td>
                    <td>${employee.idDepartment}</td>
                    <td><a href="/aimlearning_war_exploded/EmployeeServlet?action=delete&id=${employee.id}&idDepartment=${employee.idDepartment}" class="delete_button">Delete </a>
                        |
                        <a href="/aimlearning_war_exploded/EmployeeServlet?action=update&id=${employee.id}" class="update_button">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>



<a href="http://localhost:8080/aimlearning_war_exploded/addEmployeeServlet?idDepartment=${requestScope.department.idDepartment}">add to this department</a>
<a class="delete_button" href="http://localhost:8080/aimlearning_war_exploded/DepartmentServlet?action=delete&id=${requestScope.department.idDepartment}">Delete this department </a>



</body>
</html>
