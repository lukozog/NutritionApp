<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add nutrition type</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<h2>Add nutrition type</h2>

<form:form method="POST" modelAttribute="nutrition">
    <form:input type="hidden" path="nutritionId" id="nutritionId"/>
    <table>
        <tr>
            <td><label for="nutritionName">Nutrition name: </label></td>
            <td><form:input path="nutritionName" id="nutritionName"/></td>
            <td><form:errors path="nutritionName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>Input values for 100g</label></td>
        </tr>
        <tr>
            <td><label for="nutritionProteins">Nutrition proteins: </label></td>
            <td><form:input path="nutritionProteins" id="nutritionProteins"/></td>
            <td><form:errors path="nutritionProteins" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="nutritionCarbs">Nutrition carbs: </label></td>
            <td><form:input path="nutritionCarbs" id="nutritionCarbs"/></td>
            <td><form:errors path="nutritionCarbs" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="nutritionFat">Nutrition fat: </label></td>
            <td><form:input path="nutritionFat" id="nutritionFat"/></td>
            <td><form:errors path="nutritionFat" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:choose>
                    <c:when test="${add}">
                        <input type="submit" value="Add"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Update">
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </table>
</form:form>
<br/>
<br/>
<a href="<s:url value='/nutritions' />">
    <button>Show nutritions</button>
</a>
</body>
</body>
</html>
