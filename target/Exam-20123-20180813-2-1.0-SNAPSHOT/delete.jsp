<%@ page import="dao.FilmDAO" %><%--
  Created by IntelliJ IDEA.
  User: 徐
  Date: 2018/8/13
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除电影</title>
</head>
<body>
<%
    String first_name=(String)request.getSession().getAttribute("first_name");
    if(first_name!=null) {
        String id = request.getParameter("id");
        FilmDAO filmdao = new FilmDAO();
        if (id != null) {

            boolean flag = filmdao.deleteFilmById(Integer.parseInt(id));
            if (flag) {%>
                删除成功  <a href="showfilms.jsp">返回</a>"
            <%} else {%>
                删除失败  <a href="showfilms.jsp">返回</a>
           <% }
        } else {%>
            请先选择一部电影  <a href="showfilms.jsp">电影列表</a>
      <%  }
    }else {%>
                你还没有登录，即将自动跳往登录页面
       <% response.setHeader("refresh", "2;url="+"/login.jsp");
    }
%>
</body>
</html>
