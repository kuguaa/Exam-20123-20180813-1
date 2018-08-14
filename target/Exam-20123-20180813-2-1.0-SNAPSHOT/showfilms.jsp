<%--
  Created by IntelliJ IDEA.
  Film: 徐
  Date: 2018/8/13
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.Film" %>
<%@ page import="dao.FilmDAO" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>电影列表</title>
</head>
<body>
<div align="center">
    <%
        String path = request.getContextPath();
        String first_name=(String)request.getSession().getAttribute("first_name");
        if(first_name!=null) {
            FilmDAO Filmdao=new FilmDAO();
            ArrayList<Film> Films=new ArrayList<>();
            Films=Filmdao.showFilms();

    %>
    <a href="logout.jsp">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="add.jsp">新增</a>
    <table border="1">
        <tr>
            <td>电影编号</td> <td>电影名称</td> <td>电影描述</td> <td>内容语言</td>
        </tr>
        <%
            Film Film=new Film();
            for(int i=0;i<Films.size();i++){
                Film=Films.get(i);
        %>
        <tr>
            <td><%=Film.getFilm_id()%></td>
            <td><%=Film.getTitle()%></td>
            <td><%=Film.getDescription()%></td>
            <td><%=Film.getLanguage_name()%></td>
            <td><a href="edit.jsp?id=<%=Film.getFilm_id()%>">编辑</a><a href="delete.jsp?id=<%=Film.getFilm_id()%>">删除</a></td>
        </tr>
        <%} %>
    </table>
    <% }
    else{%>
        你还没有登录，即将自动跳往登录页面
        <%response.setHeader("refresh", "1;url="+path+"/login.jsp");
    } %>
</div>
</body>
</html>
