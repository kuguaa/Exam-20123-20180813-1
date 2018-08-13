<%--
  Created by IntelliJ IDEA.
  User: 徐
  Date: 2018/8/13
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销</title>
</head>
<body>
<%
    request.getSession().removeAttribute("first_name");
    response.sendRedirect("index.jsp");
%>
</body>
</html>
