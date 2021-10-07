<%@ page import="java.util.List" %>
<%@ page import="com.aimprosoft.aimlearning.model.Department" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header_refs">
    <a href="/aimlearning_war_exploded/HomePageServlet">Home page</a>
    <a href="/aimlearning_war_exploded/EmployeeServlet">All Employees</a>
    <a href="/aimlearning_war_exploded/addEmployeeServlet">Add Employee</a>
    <a href="/aimlearning_war_exploded/addDepartmentServlet">Add Department</a>
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
                    <td><a href="http://localhost:8080/aimlearning_war_exploded/DepartmentServlet?id=${department.idDepartment}"> ${department.idDepartment} </a></td>
                    <td><a href="http://localhost:8080/aimlearning_war_exploded/DepartmentServlet?id=${department.idDepartment}"> ${department.name} </a></td>
                    <td><a href="http://localhost:8080/aimlearning_war_exploded/DepartmentServlet?id=${department.idDepartment}"> ${department.address}   </a></td>
                    <td><a href="/aimlearning_war_exploded/DepartmentServlet?action=update&id=${department.idDepartment}" class="update_button">Update</a></td>
                    <td><a href="http://localhost:8080/aimlearning_war_exploded/DepartmentServlet?id=${department.idDepartment}"> List </a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


</div>


</body>
</html>