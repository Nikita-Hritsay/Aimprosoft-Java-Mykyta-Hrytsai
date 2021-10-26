<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Employees</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a class="header_ref" href="displayAllDepartments">Home page</a>
    <a class="header_ref" href="displayEmployees">All Employees</a>
    <a class="header_ref" href="createOrUpdateEmployeeForm">Add Employee</a>
    <a class="header_ref" href="createOrUpdateDepartmentForm">Add Department</a>
</div>


<div style="margin-top: 50px;">

    <c:if test="${empty departmentEmployeeMap}">
        <p>Empty</p>
    </c:if>

    <c:if test="${!empty requestScope.departmentEmployeeMap}">
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Salary</th>
                <th>Hire Date</th>
                <th>Department name</th>
            </tr>
            <c:forEach var="department" items="${requestScope.departmentEmployeeMap.keySet()}">
                <c:if test="${!empty departmentEmployeeMap.get(department)}">
                    <c:forEach var="employee" items="${requestScope.departmentEmployeeMap.get(department)}">
                        <tr>
                            <td><c:out value="${employee.firstName}"/></td>
                            <td><c:out value="${employee.lastName}"/></td>
                            <td><c:out value="${employee.email}"/></td>
                            <td><c:out value="${employee.salary}"/></td>
                            <td><c:out value="${employee.hireDate}"/></td>
                            <td><c:out value="${department.getName()}"/></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </table>
    </c:if>
</div>

</body>
</html>
