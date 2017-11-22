<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Nutrition Diary</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>


<body>
<h2>List of Users</h2>
<table>
    <tr>
        <td>userName</td>
        <td>userBirthday</td>
        <td>userHeight</td>
        <td>userWeight</td>
        <td>userSex</td>
        <td>userMail</td>
    </tr>
    <c:forEach items="${users}" var="users">
        <tr>
            <td><a href="<c:url value='/users/updateUser/${users.userId}'/>"/>
                <button>${users.userName}</button>
            </td>
            <td>${users.userBirthday}</td>
            <td>${users.userHeight}</td>
            <td>${users.userWeight}</td>
            <td>${users.userSex}</td>
            <td>${users.userMail}</td>
            <td><a href="<c:url value='/meals/${users.userId}'/>">
                <button>Show meals</button>
            </a></td>
            <td><a href="<c:url value='/users/deleteUser/${users.userId}'/>"/>
                <button>Delete</button>
            </td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<s:url value='users/newUser' />">
    <button>Add user</button>
</a>
<a href="<s:url value='/nutritions' />">
    <button>Show nutritions</button>
</a>
</body>
</html>