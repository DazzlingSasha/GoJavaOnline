<%@include file="include/header.jsp" %>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Position</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><a href="/user?id=${user.id}" >${user.firstName}</a></td>
            <td>${user.lastName}</td>
            <td>${user.positionUser}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="include/bottom.jsp" %>
