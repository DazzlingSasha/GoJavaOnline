<%@include file="header.jsp" %>

<p>MENU</p>
<table>
    <tr>
        <th>Name dish</th>
        <th>Weight</th>
        <th>Cost</th>
    </tr>
    <c:forEach items="${menu}" var="menu">
        <tr>
            <td colspan="3">${menu.category}</td>
        </tr>

        <c:forEach items="${dishes}" var="dish">
            <c:if test="${ menu.id==dish.category.id }">
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