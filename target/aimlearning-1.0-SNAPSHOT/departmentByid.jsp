<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Department</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a href="displayAllDepartments">Home page</a>
    <a href="displayEmployees">All Employees</a>
    <a href="createOrUpdateEmployeeForm">Add Employee</a>
    <a href="createOrUpdateDepartmentForm">Add Department</a>
</div>

<div class="department_by_id_manage">
    <a href="createOrUpdateEmployeeForm?idDepartment=${idDepartment}">add to this department</a>
    <a href="deleteDepartment?idDepartment=${idDepartment}">Delete this department </a>
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
                    <td><a href="deleteEmployee?&id=${employee.id}" class="delete_button">Delete </a>
                        |
                        <a href="createOrUpdateEmployeeForm?id=${employee.id}" class="update_button">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>






</body>
</html>
