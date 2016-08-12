<%@include file="include/header.jsp" %>
<table>
        <tr>
            <td content="6">
                <img src="img/smail.jpg">
            </td>
            <td>
                <p>First Name - ${user.firstName}</p>
                <p>Last Name - ${user.lastName}</p>
                <p>Birthday - ${user.birthday}</p>
                <p>Phone - ${user.phone}</p>
                <p>Position - ${user.positionUser}</p>
                <p>Salary - ${user.salary}</p>
            </td>
        </tr>
</table>
<%@include file="include/bottom.jsp" %>
