<%--@elvariable id="errors" type="java.util.Map"--%>
<%--@elvariable id="employee" type="com.aimprosoft.aimlearning.models.Employee"--%>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 08.10.21
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>

<div class="header_refs">
    <a class="header_ref" href="/displayAllDepartments">Home page</a>
    <a class="header_ref" href="/displayEmployees">All Employees</a>
    <a class="header_ref" href="/createOrUpdateEmployeeForm/">Add Employee</a>
    <a class="header_ref" href="/createOrUpdateDepartmentForm/">Add Department</a>
</div>


<div style="margin-top: 50px;" class="createOrUpdateForm">
    <form action="/createOrUpdateEmployeeForm" method="post">
        <input type="hidden" value="${employee.id}" name="id">
        Please enter your first name: <%--@declare id="iddepartments"--%><label>
        <input type="text" name="firstName" class="input_param" size="15px" value="${employee.firstName}">
    </label> <br>
        <p class="error_massage">${errors.firstName}</p>
        Please enter your last name: <label>
        <input type="text" name="lastName" class="input_param" size="15px" value="${employee.lastName}">
    </label> <br>
        <p class="error_massage">${errors.lastName}</p>
        Please enter your email: <label>
        <input type="text" name="email" class="input_param" size="15px" value="${employee.email}">
    </label> <br>
        <p class="error_massage">${errors.email}</p>
        Please enter your salary: <label>
        <input type="number" name="salary" class="input_param" size="15px" step="0.01" value="${employee.salary}">
    </label> <br><br>
        <p class="error_massage">${errors.salary}</p>
        Please enter your hire date: <label>
        <input type="date" name="hireDate" class="input_param" size="15px" value="${employee.hireDate}">
    </label> <br><br>
        <p class="error_massage">${errors.hireDate}</p>
        Please enter your department name: <label>
        <input list="idDepartments" name="department.idDepartment" class="input_param" size="15px" value="${employee.department.idDepartment}">
    </label> <br><br>
        <datalist id="idDepartments">
            <c:forEach var="department" items="${departments}">
                <option value="${department.idDepartment}" >${department.name}</option>
            </c:forEach>
        </datalist>
        <p class="error_massage">${errors.department}</p>
        <input type="submit" class="submit_createOrUpdate" value="submit">
    </form>
</div>

</body>
</html>
