<%@include file="include/header.jsp" %>

<p>MENU</p>
<table>
    <tr>
        <th>Name dish</th>
        <th>Weight</th>
        <th>Cost</th>
    </tr>
    <c:forEach items="${menu}" var="category">
        <tr>
            <td colspan="3">${category.category}</td>
        </tr>

        <c:forEach items="${dishes}" var="dish">
            <c:if test="${ category.id==dish.category.id }">
                <tr>
                    <td><a href="/dish?id=${dish.id}">${dish.name}</a></td>
                    <td>${dish.weight}</td>
                    <td>${dish.cost}</td>
                </tr>
            </c:if>
        </c:forEach>

    </c:forEach>

</table>
<%@include file="include/bottom.jsp" %>