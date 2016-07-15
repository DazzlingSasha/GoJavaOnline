<%@ page import="java.util.Enumeration" %>
<%@ page import="com.todoList.servlets.Task" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="index.jsp?update" method="post">
    <table width="60%" border="1" align="center">
        <tr>
            <th>Name</th>
            <th>Category</th>
            <th>Complete</th>
        </tr>
        <%Task.getTask();
            System.out.println(Task.getTask().size());
        %>
        <%--<c:forEach items="${requestScope.size()}" var="headerName">--%>
            <%--<tr>--%>
                <%--<td>    <c:out value="${headerName.key}"/>                  </td>--%>
                <%--<td>    <c:out value="${headerName.value}"/>      </td>--%>
                <%--<td>    <input type="checkbox" name="${headerName}" />  </td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    </table>
    <input type="submit" value="Update Task"/>
</form>

<form action="index.jsp?add" method="POST">
    <table width="50%" border="0">
        <tr>
            <td>Task name:</td>
            <td><input name="name" type="text"/></td>
        </tr>
        <tr>
            <td>Task category:</td>
            <td><input name="category" type="text"/> </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add Task"/> </td>
        </tr>
    </table>
</form>
</body>
</html>
