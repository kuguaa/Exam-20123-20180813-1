<%--
  Created by IntelliJ IDEA.
  User: 徐
  Date: 2018/8/13
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="GBK" language="java" %>

<html>
<head>
    <title>新增</title>
</head>
<body>
<div align="center">
    <%
        String path = request.getContextPath();
        String first_name=(String)request.getSession().getAttribute("first_name");
        if(first_name!=null && !first_name.isEmpty()) {
    %>
        <label>添加新电影</label>
        <form action="AddFilmServlet" method="post">
            <label>电影名称：</label>
            <input type="text" name="title" id="title" required><br/>
            <label>添加描述：</label>
            <textarea name="description" id="description" rows="3" cols="20" required></textarea>
            <br/>
            <label>选择语言：</label>
            <select name="language" id="language" required>
                <option value="1">English</option>
                <option value="2">Italian</option>
                <option value="3">Japanese</option>
                <option value="4">Mandarin</option>
                <option value="5">French</option>
                <option value="6">German</option>
            </select><br/>
            <button type="reset">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit">提交</button>
        </form>
        <%
            String err=(String)request.getSession().getAttribute("err");
            if(err!=null && !err.isEmpty()){
        %>
                <div style="color:red;"><%=err %></div>
    <%          request.getSession().removeAttribute("err");
            }
        } else{
            out.print("你还没有登录，即将自动跳往登录页面");
            response.setHeader("refresh", "2;url="+path+"/login.jsp");
        }
    %>
</div>
</body>
</html>
