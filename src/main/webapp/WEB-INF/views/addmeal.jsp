<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meal form</title>
    <style>

        .error {
            color: #ff0000;
        }
    </style>
</head>
<br>
<body>
<h2>Add meal</h2>


<form:form method="POST" modelAttribute="meal">
    <form:input type="hidden" path="mealId" id="mealId"/>
    <table>
        <tr>
            <td><label for="mealName">Meal Name</label></td>
            <td><form:input path="mealName" id="mealName"/></td>
            <td><form:errors path="mealName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="mealDate">Meal Date: </label>
            <td><form:input type="date" path="mealDate" id="mealDate"/></td>
            <td><form:errors path="mealDate" cssClass="error"/>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${add}">
                    <td><input type="submit" value="Add"/></td>
                </c:when>
                <c:otherwise>
                    <td><input type="submit" value="Update"></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>
</form:form>

<a href="<s:url value='/meals/${userId}'/>">
    <button>Show meals</button>
</a>

</body>
</html>