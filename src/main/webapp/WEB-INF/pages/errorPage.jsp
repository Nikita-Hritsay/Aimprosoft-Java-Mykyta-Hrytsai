<jsp:useBean id="error" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a class="header_ref" href="/displayAllDepartments">Home page</a>
    <a class="header_ref" href="/displayEmployees">All Employees</a>
    <a class="header_ref" href="/createOrUpdateEmployeeForm">Add Employee</a>
    <a class="header_ref" href="/createOrUpdateDepartmentForm">Add Department</a>
</div>


<div>
    <h4>${error}</h4>
</div>

</body>
</html>