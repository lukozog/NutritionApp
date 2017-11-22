<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nutritions of meal</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
</head>
<body>
<h2>List of meal nutritions</h2>
<table>
    <tr>
        <td>nutritionName</td>
        <td>nutritionCalories</td>
        <td>nutritionProteins</td>
        <td>nutritionCarbs</td>
        <td>nutritionFat</td>
    </tr>
    <c:forEach items="${mealNutritions}" var="mealNutritions">
        <tr>
            <td>${mealNutritions.mealNutritionName}</td>
            <td>${mealNutritions.mealNutritionCalories}</td>
            <td>${mealNutritions.mealNutritionProteins}</td>
            <td>${mealNutritions.mealNutritionCarbs}</td>
            <td>${mealNutritions.mealNutritionFat}</td>
            <td>
                <a href="<c:url value='/mealNutrition/deleteNutrition/${userId}/${mealId}/${mealNutritions.mealNutritionId}' />">
                    <button>Delete</button>
                </a></td>
        </tr>
    </c:forEach>
</table>

<a href="<s:url value='/mealNutrition/newMealNutrition/${userId}/${mealId}'/>">
    <button>Add nutrition</button>
</a>
<a href="<s:url value='/meals/${userId}'/>">
    <button>Show meals</button>
</a>
</body>
</html>
