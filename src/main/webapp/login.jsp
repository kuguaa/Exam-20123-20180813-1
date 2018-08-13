<%--
  Created by IntelliJ IDEA.
  User: 徐
  Date: 2018/8/13
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <div align="center">
        <%
            String first_name=(String)request.getSession().getAttribute("first_name");
            if(first_name==null) {
        %>
                <label>请输入用户名：</label>
                <form action="LoginServlet" method="post">
                    <input type="text" name="first_name" id="first_name" required/><br/>
                    <br/>
                    <button type="reset">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit">提交</button>
                </form>
        <%
                String errMsg=(String)session.getAttribute("err");
                if( errMsg!=null ) {%>
                    <div style="color:red;"><%=errMsg %></div>
                    <%  session.removeAttribute("err");
                }
            }else {
                out.print("您的状态为已登录   <a href='index.jsp'>返回</a>");
            } %>
    </div>
</body>
</html>
