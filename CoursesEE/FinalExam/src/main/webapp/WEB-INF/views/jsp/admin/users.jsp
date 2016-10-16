<%@include file="header.jsp" %>
<p>Admin MENU</p>

<c:url var="addAction" value="/admin/user/add" ></c:url>
<form:form action="${addAction}" commandName="user">
    <c:if test="${!empty user.id}">
        <tr>
        <td>
            <form:label path="id">ID</form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="4"  disabled="true" />
            <form:hidden path="id" />
        </td>
    </c:if>
    <td>
        <label><spring:message text="First name"/></label>
        <input size="20" name="firstName" value="${user.firstName}"/>
    </td>
    <td>
        <label><spring:message text="Last name"/></label>
        <input size="20" name="lastName" value="${user.lastName}"/>
    </td>
    <td>
        <label><spring:message text="Birthday"/></label>
        <input size="20" name="birthday" value="${user.birthday}"/>
    </td>
    <td>
        <label><spring:message text="Phone"/></label>
        <input size="20" name="phone" value="${user.phone}"/>
    </td>
    <td>
        <label>Position</label>
        <select name="positionUser">
            <c:if test="${!empty user.positionUser}">
                <option value="${user.positionUser}">${user.positionUser}</option>
            </c:if>
            <c:if test="${empty user.positionUser}">
                <option value="NONE">--- Select ---</option>
            </c:if>
            <c:forEach items="${position}" var="position">
                <option value="${position}">${position.name()}</option>
            </c:forEach>
        </select>
    </td>
    <td>
        <label><spring:message text="Salary"/></label>
        <input size="20" name="salary" value="${user.salary}"/>
    </td>

    <td colspan="2">
        <c:if test="${!empty user.id}">
            <input type="submit" value="Edit user"/>
        </c:if>
        <c:if test="${empty user.id}">
            <input type="submit" value="Add user"/>
        </c:if>
    </td>
    </tr>

</form:form>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Birthday</th>
        <th>Phone</th>
        <th>Position</th>
        <th>Salary</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.birthday}</td>
            <td>${user.phone}</td>
            <td>${user.positionUser}</td>
            <td>${user.salary}</td>
            <td><a href="/admin/user/edit/${user.id}" >Edit</a></td>
            <td><a href="/admin/user/remove/${user.id}" >Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
