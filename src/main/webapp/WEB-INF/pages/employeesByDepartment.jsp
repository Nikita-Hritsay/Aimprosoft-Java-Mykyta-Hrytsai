<jsp:useBean id="idDepartment" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="department" scope="request" type="com.aimprosoft.aimlearning.models.Department"/>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Department</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>

<div class="header_refs">
    <a class="header_ref" href="/displayAllDepartments">Home page</a>
    <a class="header_ref" href="/displayEmployees">All Employees</a>
    <a class="header_ref" href="/createOrUpdateEmployeeForm/">Add Employee</a>
    <a class="header_ref" href="/createOrUpdateDepartmentForm/">Add Department</a>
</div>


<div class="department_by_id_manage">
    <a class="header_ref" href="createOrUpdateEmployeeForm?idDepartment=${idDepartment}">add to this department</a>
    <form action="deleteDepartment" method="post">
        <input type="hidden" value="${department.idDepartment}" name="idDepartment">
        <input class="delete_button submit_delete" type="submit" value="Delete">
    </form>
</div>

<div style="margin-top: 50px;">
    <c:if test="${empty department.employees}">
        <p>Empty</p>
    </c:if>
    <c:if test="${!empty department.employees}">
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Salary</th>
                <th>Hire Date</th>
                <th>Department id</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="employee" items="${department.employees}">
                <tr>
                    <td><c:out value="${employee.firstName}"/></td>
                    <td><c:out value="${employee.lastName}"/></td>
                    <td><c:out value="${employee.email}"/></td>
                    <td><c:out value="${employee.salary}"/></td>
                    <td><c:out value="${employee.hireDate}"/></td>
                    <td><c:out value="${employee.department.idDepartment}"/></td>
                    <td><a href="createOrUpdateEmployeeForm?id=${employee.id}" class="update_button"> Update </a></td>

                    <td>
                        <form action="deleteEmployee" method="post">
                            <input type="hidden" value="${employee.id}" name="id">
                            <input type="hidden" value="${employee.department.idDepartment}" name="idDepartment">
                            <input class="delete_button submit_delete" type="submit" value="Delete">
                        </form>
                    </td>


                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>


</body>
</html>
