<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 11:33
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
    <a href="/aimlearning_war_exploded/allEmployees.jsp">All Employees</a>
    <a href="/aimlearning_war_exploded/addEmployee.jsp">Add Employee</a>
    <a href="/aimlearning_war_exploded/addDepartment.jsp">Add Department</a>
</div>

<%@ page import="java.util.List" %>
<%@ page import="com.aimprosoft.aimlearning.model.Employee" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl" %>
<%@ page import="java.io.PrintWriter" %>

<div align="center" style="margin-top: 50px;">

    <table border=\"1\">

    <%
        List<Employee> employees = new EmployeeDAOImpl().getAllEmployees();
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<table border=\"1\">\n" +
                "        <tr>\n" +
                "            <th>Id</th>\n" +
                "            <th>First Name</th>\n" +
                "            <th>Last Name</th>\n" +
                "            <th>Email</th>\n" +
                "            <th>Salary</th>\n" +
                "            <th>Hire Date</th>\n" +
                "            <th>Department id</th>\n" +
                "        </tr>");
        for(int i = 0 ; i < employees.size(); i++){
            printWriter.println("<tr>" + "<td>" + employees.get(i).getId() + "</td>" +
                    "<td>" + employees.get(i).getFirstName() + "</td>" +
                    "<td>" + employees.get(i).getLastName() + "</td>" +
                    "<td>" + employees.get(i).getEmail() + "</td>" +
                    "<td>" + employees.get(i).getSalary() + "</td>" +
                    "<td>" + employees.get(i).getHireDate() + "</td>" +
                    "<td>" + employees.get(i).getIdDepartment() + "</td>" + "</tr>");
        }
        printWriter.println("</table>");
    %>
</div>

</body>
</html>
