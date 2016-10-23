<%@include file="header.jsp" %>
<p>Admin MENU</p>

<form:form action="/admin/history/name" commandName="history" method="post">
    <label>Seartch by name waiter</label>
    <input size="20" name="nameWaiter" value="${itemOrder.idOrder.nameUser}"/>
    <input type="submit" value="Find"/>
</form:form>
<form:form action="/admin/history/table" commandName="history" method="get">
    <label>Seartch all orders by number table</label>
    <input size="20" name="numberTable" value="${itemOrder.idOrder.numberTable}"/>
    <input type="submit" value="Find"/>
</form:form>
<form:form action="/admin/history/date" commandName="history" method="get">
    <label>Seartch by date</label>
    <input size="20" name="date" value="${itemOrder.idOrder.dateOrder}"/>
    <input type="submit" value="Find"/>
</form:form>

<table class="table_history">
    <tr>
        <th>Id</th>
        <th>Name Dish</th>
        <th>Waiter</th>
        <th>Date Give</th>
        <th>Number Table</th>
        <th>Order(Open/Close)</th>
        <th>Cook</th>
        <th>Data Prepared</th>
    </tr>

    <c:forEach items="${history}" var="itemWithHistory">
        <tr>
            <td>${itemWithHistory.id}</td>
            <td>${itemWithHistory.idDish.name}</td>
            <td>${itemWithHistory.idUser.firstName} ${itemWithHistory.idUser.lastName}</td>
            <td>${itemWithHistory.idOrder.dateOrder}</td>
            <td>${itemWithHistory.idOrder.numberTable}</td>
            <td>${itemWithHistory.idOrder.closeOrOpenOrder}</td>
            <td>${itemWithHistory.prepared.firstName} ${itemWithHistory.prepared.lastName}</td>
            <td>${itemWithHistory.datePreparedDish}</td>
        </tr>
    </c:forEach>

</table>
<%@include file="../include/bottom.jsp" %>