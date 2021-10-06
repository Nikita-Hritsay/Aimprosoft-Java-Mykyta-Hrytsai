<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.aimprosoft.aimlearning.model.Department" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 11:53
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
    <a href="/aimlearning_war_exploded/addDepartment.jsp">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">



    <c:if test="${!empty wrong}">
        <p>${wrong}</p>
    </c:if>

    <form action="addEmployeeServlet" method="post">
        Please enter your first name:  <%--@declare id="iddepartments"--%><input type="text" name="firstName" class="input_param" size="15px" value="${firstName}"> <br>
        Please enter your last name:  <input type="text" name="lastName" class="input_param" size="15px" value="${lastName}"> <br>
        Please enter your email:  <input type="text" name="email" class="input_param" size="15px" value="${email}"> <br>
        Please enter your salary:  <input type="number" name="salary" class="input_param" size="15px" min="0" value="${salary}"> <br><br>
        Please enter your hire date:  <input type="date" name="hireDate" class="input_param" size="15px" value="${hireDate}"> <br><br>
        Please enter your department id:  <input list="idDepartments" name="iddepartment" class="input_param" size="15px" value="${requestScope.idDepartment}"> <br><br>
        <datalist id="idDepartments">
            <c:forEach var="department" items="${requestScope.departments}">
                <option value="${department.idDepartment}" >
            </c:forEach>
        </datalist>

        <input type="submit" value="submit">
    </form>
</div>
</body>
</html>
