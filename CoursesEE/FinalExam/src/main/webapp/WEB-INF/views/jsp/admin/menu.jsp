<%@ include file="header.jsp" %>

<p>Admin MENU</p>

<c:url var="addAction" value="/admin/menu/add" ></c:url>
<form:form action="${addAction}" commandName="menu">
    <c:if test="${!empty menu.id}">
        <tr>
            <td>
                <form:label path="id">ID</form:label>
            </td>
            <td>
                <form:input path="id" readonly="true" size="8"  disabled="true" />
                <form:hidden path="id" />
            </td>
    </c:if>
            <td>
                <form:label path="category">
                    <spring:message text="Category"/>
                </form:label>
            </td>
            <td>
                <form:input path="category" />
            </td>
            <td colspan="2">
                <c:if test="${!empty menu.category}">
                    <input type="submit" value="Edit category"/>
                </c:if>
                <c:if test="${empty menu.category}">
                    <input type="submit" value="Add category"/>
                </c:if>
            </td>
        </tr>

</form:form>


<table>
    <tr>
        <th>Name category</th>

    </tr>
    <c:forEach items="${menus}" var="menu">
        <tr>
            <td>
            <td>${menu.category}</td>
            <td><a href="<c:url value='/admin/menu/edit/${menu.id}' />" >Edit</a></td>
            <td><a href="<c:url value='/admin/menu/remove/${menu.id}' />" >Delete</a></td>
        </tr>

        <c:forEach items="${dishes}" var="dish">
            <c:if test="${ menu.id == dish.category.id }">
                <tr>
                    <td><a href="/dish?id=${dish.id}">${dish.name}</a></td>
                    <td>${dish.weight}</td>
                    <td>${dish.cost}</td>
                </tr>
            </c:if>
        </c:forEach>

    </c:forEach>
</table>
</body>
</html>