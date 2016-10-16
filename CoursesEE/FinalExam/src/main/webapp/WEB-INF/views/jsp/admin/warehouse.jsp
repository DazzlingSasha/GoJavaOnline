<%@include file="header.jsp" %>
<p>Admin WAREHOUSE</p>

<p>Add new Ingredient</p>
<c:url var="addAction" value="/admin/warehouse/add"></c:url>
<form:form action="${addAction}" commandName="ingredient">
    <c:if test="${!empty ingredient.id}">
        <tr>
        <td>
            <form:label path="id">ID</form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="4" disabled="true"/>
            <form:hidden path="id"/>
        </td>
    </c:if>
    <td>
        <label><spring:message text="Name ingredient"/></label>
        <input size="20" name="name" value="${ingredient.name}"/>
    </td>
    <td>
        <label><spring:message text="Unit"/></label>
        <input size="20" name="unit" value="${ingredient.unit}"/>
    </td>

    <td>
        <input type="submit" value="Add ingredient"/>
    </td>
    </tr>

</form:form>

<p>Search in the warehouse by name ingredients:</p>

<form:form action="/admin/warehouse" commandName="warehouses" method="post">
        <label>Seartch by name</label>
        <input size="20" name="name" value="${ingredient.name}"/>
        <input type="submit" value="Find"/>
</form:form>

<form:form action="/admin/warehouse" commandName="warehouses" method="get">
        <input type="submit" value="All warhouse"/>
</form:form>

<p>Select ingredients</p>

<table>
    <tr>
        <th>Id</th>
        <th>Name ingredients</th>
        <th>Quantity</th>
        <th>Unit</th>
        <th></th>
    </tr>
    <c:forEach items="${warehouses}" var="warehouseItem">
        <tr>
            <td>${warehouseItem.id}</td>
            <td>${warehouseItem.idIngredient.name}</td>
            <td>
                <form action="/admin/warehouse/edit/${warehouseItem.id}" commandName="editIngredient" method="post">
                    ${warehouseItem.quantity}
                    <label>Edit quantity</label>
                    <input size="5" name="quantity"/>
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>${warehouseItem.idIngredient.unit}</td>
            <td><a href="/admin/warehouse/delete/${warehouseItem.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
