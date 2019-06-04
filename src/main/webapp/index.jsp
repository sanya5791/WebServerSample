<%--
  Created by IntelliJ IDEA.
  User: sanya
  Date: 2019-05-29
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        th, td {
            border: 1px solid black;
            padding: 4px;
            padding-left: 16px;
            padding-right: 16px;
        }
    </style>

</head>
<body>

<h2>Usage:</h2>
<table>
    <tr>
        <th>Endpoint</th>
        <th>Description</th>
    </tr>

    <tr>
        <td><a href="${pageContext.request.contextPath}/headers">/headers</a></td>
        <td>show all headers</td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/sanya?login=SanyaLogin&password=SanyaPassword">/sanya</a></td>
        <td>show Sanya's personal details</td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/register_user">/register_user</a></td>
        <td>register new user</td>
    </tr>

</table>

</body>
</html>
