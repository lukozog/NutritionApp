<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add User</title>
</head>
<style>

    .error {
        color: #ff0000;
    }
</style>
<body>
<h2>Add user</h2>

<form:form method="POST" modelAttribute="user">
    <form:input type="hidden" path="userId" id="userId"/>
    <table>
        <tr>
            <td><label for="userName">User name: </label></td>
            <td><form:input path="userName" id="userName"/></td>
            <td><form:errors path="userName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="userBirthday">User birthday: </label></td>
            <td><form:input type="date" path="userBirthday" id="userBirthday"/></td>
            <td><form:errors path="userBirthday" cssClass="error"/>
        </tr>
        <tr>
            <td><label for="userHeight">User height: </label></td>
            <td><form:input path="userHeight" id="userHeight"/></td>
            <td><form:errors path="userHeight" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="userWeight">User weight: </label></td>
            <td><form:input path="userWeight" id="userWeight"/></td>
            <td><form:errors path="userWeight" cssClass="error"/></td>
        </tr>
        <td><label for="userMail">User mail: </label></td>
        <td><form:input path="userMail" id="userMail"/></td>
        <td><form:errors path="userMail" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="userSex">User sex: </label></td>
            <td>

                <form:select path="userSex">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </form:select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:choose>
                    <c:when test="${add}">
                        <input type="submit" value="Add"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Update"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>


    </table>
</form:form>
<br/>
<br/>
<a href="<s:url value='/users' />">
    <button>Show users</button>
</a>
</body>
</body>
</html>
