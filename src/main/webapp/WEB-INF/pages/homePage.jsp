<%@ page import="java.util.List" %>
<%@ page import="com.aimprosoft.aimlearning.models.Department" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl" %>
<%@ page import="java.io.PrintWriter" %>
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
    <a class="header_ref" href="displayAllDepartments">Home page</a>
    <a class="header_ref" href="displayEmployees">All Employees</a>
    <a class="header_ref" href="createOrUpdateEmployeeForm">Add Employee</a>
    <a class="header_ref" href="createOrUpdateDepartmentForm">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">

    <c:if test="${empty departments}">
        <p>Empty</p>
    </c:if>

    <c:if test="${!empty departments}">
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Address</th>
                <th>Action</th>
                <th>List</th>
            </tr>
            <c:forEach var="department" items="${departments}">
                <tr>
                    <td><a href="employeesByDepartment?id=${department.idDepartment}"> ${department.idDepartment} </a></td>
                    <td><a href="employeesByDepartment?id=${department.idDepartment}"> <c:out value="${department.name}" /> </a></td>
                    <td><a href="employeesByDepartment?id=${department.idDepartment}"> <c:out value="${department.address}" /> </a></td>
                    <td><form action="deleteDepartment">
                            <input type="hidden" value="${department.idDepartment}" name="idDepartment">
                            <input class="delete_button" type="submit" value="Delete">
                        </form>

                        <a href="createOrUpdateDepartmentForm?id=${department.idDepartment}" class="update_button">Update</a></td>
                    <td><a href="employeesByDepartment?id=${department.idDepartment}"> List </a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


</div>


</body>
</html>