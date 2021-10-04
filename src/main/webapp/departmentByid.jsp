<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.09.21
  Time: 14:14
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
<%@ page import="com.aimprosoft.aimlearning.model.Department" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl" %>
<%@ page import="com.aimprosoft.aimlearning.DAO.DepartmentDAO" %>
<%@ page import="java.sql.PreparedStatement" %>

<div align="center" style="margin-top: 50px;">
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        List<Employee> employees = new DepartmentDAOImpl().getById(Integer.parseInt(request.getParameter("id")));
        PrintWriter printWriter = response.getWriter();
        Department department = new DepartmentDAOImpl().findDepartmentById(Integer.parseInt(request.getParameter("id")));
        printWriter.println(department.toString());
        printWriter.println();
        for(int i = 0 ; i < employees.size(); i++){
            printWriter.println("<p>" + employees.get(i).toString() +
                    "<a class=\"delete_button\" href=\"/aimlearning_war_exploded/DeleteEmployeeServlet?id=" + employees.get(i).getId()+
                    "&idDepartment=" + department.getIdDepartment() + "\">    Delete this employee  </a>" +
                    "<a class=\"update_button\" href=\"/aimlearning_war_exploded/updateEmployeeServlet?id=" + employees.get(i).getId() +
                    "&idDepartment=" + department.getIdDepartment() +
                    "&firstName=" + employees.get(i).getFirstName() +
                    "&lastName=" + employees.get(i).getLastName() +
                    "&salary=" + employees.get(i).getSalary() +
                    "&hireDate=" + employees.get(i).getHireDate() + "\">    Update this employee</a> </p> " );
        }
    %>
</div>

<%
    printWriter.println("<a href=\"http://localhost:8080/aimlearning_war_exploded/addEmployee.jsp?id=" + id +"&idDepartment=" + Integer.parseInt(request.getParameter("id")) + "\">add to this department</a>");
    printWriter.println("<a class=\"delete_button\" href=\"http://localhost:8080/aimlearning_war_exploded/deleteDepartmentServlet?id=" + request.getParameter("id")+ "\">    Delete this department  </a>");
%>


</body>
</html>
