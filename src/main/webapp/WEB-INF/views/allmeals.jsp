<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User meals</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>


<body>
<h2>List of meals</h2>
<table>
    <tr>
        <td>mealName</td>
        <td>mealDate</td>
        <td>mealCalories</td>
        <td>mealProteins</td>
        <td>mealCarbs</td>
        <td>mealFat</td>
    </tr>
    <c:forEach items="${meals}" var="meals">
        <tr>
            <td><a href="<c:url value='/meals/updateMeal/${userId}/${meals.mealId}'/>"/>
                <button>${meals.mealName}</button>
            </td>
            <td>${meals.mealDate}</td>
            <td>${meals.mealCalories}</td>
            <td>${meals.mealProteins}</td>
            <td>${meals.mealCarbs}</td>
            <td>${meals.mealFat}</td>
            <td><a href="<c:url value='/mealNutritions/${userId}/${meals.mealId}'/>">
                <button>Add nutrition</button>
            </a></td>
            <td><a href="<c:url value='/meals/deleteMeal/${userId}/${meals.mealId}'/>">
                <button>Delete meal</button>
            </a></td>


        </tr>
    </c:forEach>
</table>
<br/>

<a href="<s:url value='/meals/newMeal/${userId}'/>">
    <button>Add meal</button>
</a>
<a href="<s:url value='/users'/>">
    <button>Show users</button>
</a>


</body>
</html>