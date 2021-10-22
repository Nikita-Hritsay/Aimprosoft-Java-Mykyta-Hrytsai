<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 08.10.21
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
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

<div align="center" style="margin-top: 50px;">
    <div align="center" style="margin-top: 50px;">
        <form action="createOrUpdateEmployee" method="post">
            <input type="hidden" name="id" class="input_param" size="20px" value="${employee.id}">
            Please enter your first name:  <%--@declare id="iddepartments"--%><input type="text" name="firstName" class="input_param" size="15px" value="${employee.firstName}"> <br>
            <p class="error_massage">${errors.get("firstName")}</p>
            Please enter your last name:  <input type="text" name="lastName" class="input_param" size="15px" value="${employee.lastName}"> <br>
            <p class="error_massage">${errors.get("lastName")}</p>
            Please enter your email:  <input type="text" name="email" class="input_param" size="15px" value="${employee.email}"> <br>
            <p class="error_massage">${errors.get("email")}</p>
            Please enter your salary:  <input type="number" name="salary" class="input_param" size="15px" value="${employee.salary}"> <br><br>
            <p class="error_massage">${errors.get("salary")}</p>
            Please enter your hire date:  <input type="date" name="hireDate" class="input_param" size="15px" value="${employee.hireDate}"> <br><br>
            <p class="error_massage">${errors.get("hireDate")}</p>
            Please enter your department id:  <input list="idDepartments" name="iddepartment" class="input_param" size="15px" value="${idDepartment}"> <br><br>
            <datalist id="idDepartments">
                <c:forEach var="department" items="${requestScope.departments}">
                <option value="${department.name}" name="${department}" >
                    </c:forEach>
            </datalist>
            <p class="error_massage">${errors.get("idDepartment")}</p>
            <input type="submit" value="submit">
        </form>

    </div>
</div>

</body>
</html>
