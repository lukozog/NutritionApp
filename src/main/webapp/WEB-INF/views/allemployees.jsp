<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>University Enrollments</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>


<body>
<h2>List of Employees</h2>
<table>
    <tr>
        <td>NAME</td>
        <td>Joining Date</td>
        <td>Salary</td>
        <td>SSN</td>
        <td></td>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.joiningDate}</td>
            <td>${employee.salary}</td>
            <td><a href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td>
            <td><a href="<c:url value='/delete-${employee.id}-employee' />">delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<s:url value='/new' />">Add New Employee</a>
<a href="<s:url value='/showusers'/>">Show all users</a>
</body>
</html>
