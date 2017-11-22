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
<h2>List of nutritions</h2>
<table>
    <tr>
        <td>nutritionName</td>
        <td>nutritionCalories</td>
        <td>nutritionProteins</td>
        <td>nutritionCarbs</td>
        <td>nutritionFat</td>
    </tr>
    <c:forEach items="${allNutritions}" var="allNutritions">
        <tr>
            <td><a href="<c:url value='/nutritions/updateNutrition/${allNutritions.nutritionId}'/>"/>
                <button>${allNutritions.nutritionName}</button>
            </td>
            <td>${allNutritions.nutritionCalories}</td>
            <td>${allNutritions.nutritionProteins}</td>
            <td>${allNutritions.nutritionCarbs}</td>
            <td>${allNutritions.nutritionFat}</td>
            <td><a href="<c:url value='/nutritions/deleteNutrition/${allNutritions.nutritionId}'/>"/>
                <button>Delete meal</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<s:url value='/nutritions/newNutrition' />">
    <button>Add nutrition</button>
</a>
<a href="<s:url value='/users' />">
    <button>Show users</button>
</a>
</body>
</html>