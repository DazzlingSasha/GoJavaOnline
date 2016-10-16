<%@ page import="restaurant.model.Ingredient" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@include file="header.jsp" %>

<p>Admin DISH</p>

<form:form action="/admin/dish/add" commandName="dish" method="post">
    <c:if test="${!empty dish.id}">
        <tr>
        <td>
            <form:label path="id">ID</form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="5" disabled="true"/>
            <form:hidden path="id"/>
        </td>
    </c:if>
    <td>
        <label><spring:message text="Name"/></label>
        <input size="20" name="name" value="${dish.name}"/>
    </td>
    <td>
        <label>Category</label>
        <select name="menu_id">
            <c:if test="${!empty dish.category}">
                <option value="${dish.category.id}">${dish.category}</option>
            </c:if>
            <c:if test="${empty dish.category}">
                <option value="NONE">--- Select ---</option>
            </c:if>
            <c:forEach items="${menus}" var="menu">
                <option value="${menu.id}">${menu.category}</option>
            </c:forEach>
        </select>
    </td>
    <td>
        <label><spring:message text="Cost"/></label>
        <input size="5" name="cost" value="${dish.cost}"/>
    </td>
    <td>
        <label><spring:message text="Weight"/></label>
        <input size="5" name="weight" value="${dish.weight}"/>
    </td>

    <td colspan="2">
        <c:if test="${!empty dish.name}">
            <input type="submit" value="Edit dish"/>
        </c:if>
        <c:if test="${empty dish.name}">
            <input type="submit" value="Add dish"/>
        </c:if>
    </td>
    </tr>

</form:form>


<table class="table_dish_admin">
    <tr>
        <td>Name</td>
        <td>Category</td>
        <td>Cost</td>
        <td>Weight</td>
        <td>Ingredients</td>
        <td></td>
        <td></td>
    </tr>

    <c:forEach items="${dishes}" var="dish">
        <% List<Ingredient> listIngredientsNotPut = new ArrayList<>(); %>
        <tr>
            <td>${dish.name}</td>
            <td>${dish.category.category}</td>
            <td>${dish.cost}</td>
            <td>${dish.weight}</td>
            <td>
                <ul>
                    <c:forEach items="${dishIngredients}" var="allIngredient">
                        <c:forEach items="${dish.idIngredient}" var="ingredient">
                            <c:if test="${allIngredient.idDish == dish.id && allIngredient.idIngredient.id == ingredient.id}">
                                <li>
                                    ${ingredient.name} - ${allIngredient.quantity} ${ingredient.unit}
                                    <a href="<c:url value='/admin/dish/delete/${allIngredient.id}' />">Delete</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </ul>
                <form action="/admin/dish/add_ingredient/${dish.id}" commandName="dish" method="post">
                    <label>Ingredients:</label>
                    <select name="ingredient">
                        <c:forEach items="${ingredients}" var="ingredient">
                            <c:if test="${!dish.idIngredient.contains(ingredient)}">
                                <option value="${ingredient.id}">${ingredient.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <label>Quantity:</label>
                    <input size="5" name="quantity"/>
                    <input type="submit" value="Add ingredient"/>
                </form>
            </td>
            <td><a href="<c:url value='/admin/dish/edit/${dish.id}' />">Edit</a></td>
            <td><a href="<c:url value='/admin/dish/remove/${dish.id}' />">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
