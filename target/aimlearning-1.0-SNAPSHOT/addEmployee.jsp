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
    <a href="/aimlearning_war_exploded/allEmployees.jsp">All Employees</a>
    <a href="/aimlearning_war_exploded/addEmployee.jsp">Add Employee</a>
    <a href="/aimlearning_war_exploded/addDepartment.jsp">Add Department</a>
</div>

<div align="center" style="margin-top: 50px;">

    <%
        if(request.getParameter("wrong") != null){
            PrintWriter printWriter = response.getWriter();
            printWriter.println(request.getParameter("wrong"));
        }
    %>


    <form action="addEmployeeServlet" method="post">
        Please enter your first name:  <%--@declare id="iddepartments"--%><input type="text" name="firstName" class="input_param" size="15px"> <br>
        Please enter your last name:  <input type="text" name="lastName" class="input_param" size="15px"> <br>
        Please enter your salary:  <input type="number" name="salary" class="input_param" size="15px"> <br><br>
        Please enter your hire date:  <input type="date" name="hireDate" class="input_param" size="15px"> <br><br>

        Please enter your department id:  <input list="idDepartments" name="iddepartment" class="input_param" size="15px">
            <%
                PrintWriter printWriter = response.getWriter();
                printWriter.println("<datalist id=\"idDepartments\">");
                for(Department department: new DepartmentDAOImpl().getAllDepartments()){
                    System.out.println(department.getIdDepartment());
                    printWriter.println("<option value=\"" + department.getIdDepartment() + "\"></option>");
                }
                printWriter.println("</datalist>");
            %>


        <input type="submit" value="submit">
    </form>
</div>
</body>
</html>
