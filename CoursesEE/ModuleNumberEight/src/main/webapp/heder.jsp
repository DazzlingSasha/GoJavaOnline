<%@ page import="java.util.Enumeration" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <table width="60%" border="1" align="center">
        <tr>
            <th>Name</th>
            <th>Values</th>
        </tr>
        <c:forEach items="${header.keySet()}" var="headerName">
            <tr>
                <td>
                    <c:out value="${headerName}"/>
                </td>
                <td>
                    <c:out value="${header.get(headerName)}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
