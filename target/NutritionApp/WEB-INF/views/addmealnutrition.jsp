<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meal nutrition form</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>
</head>
<br>
<body>
<h2>Nutritions list</h2>
<table>
    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
    <tr>
        <td>nutritionName</td>
        <td>nutritionCalories</td>
        <td>nutritionProteins</td>
        <td>nutritionCarbs</td>
        <td>nutritionFat</td>
    </tr>
    <c:forEach items="${nutritions}" var="nutritions">
        <tr>
            <td>${nutritions.nutritionName}</td>
            <td>${nutritions.nutritionCalories}</td>
            <td>${nutritions.nutritionProteins}</td>
            <td>${nutritions.nutritionCarbs}</td>
            <td>${nutritions.nutritionFat}</td>
        </tr>
    </c:forEach>
</table>
<br>


<h2>Add nutrition</h2>
<form:form method="POST" modelAttribute="mealNutrition">
    <form:input type="hidden" path="mealNutritionId" id="mealNutritionId"/>
    <table>
        <tr>
            <td>
                <form:form method="POST" modelAttribute="nutrition">
                    <label for="nutritionId">Choose nutrition </label>
                    <form:select path="nutritionId">
                        <form:options items="${nutritions}" itemValue="nutritionId" itemLabel="nutritionName"/>
                    </form:select>
                </form:form>
            </td>

        </tr>
        <tr>
            <td><label for="mealNutritionValue">Grams of nutrition </label></td>
            <td><form:input path="mealNutritionValue" id="mealNutritionValue"/></td>
            <td><form:errors path="mealNutritionValue" cssClass="error"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"/></td>
        </tr>
    </table>
</form:form>
<br/>
<br/>


</body>
</html>
