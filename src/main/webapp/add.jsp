<%--
  Created by IntelliJ IDEA.
  User: ��
  Date: 2018/8/13
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="GBK" language="java" %>

<html>
<head>
    <title>����</title>
</head>
<body>
<div align="center">
    <%
        String path = request.getContextPath();
        String first_name=(String)request.getSession().getAttribute("first_name");
        if(first_name!=null && !first_name.isEmpty()) {
    %>
        <label>����µ�Ӱ</label>
        <form action="AddFilmServlet" method="post">
            <label>��Ӱ���ƣ�</label>
            <input type="text" name="title" id="title" required><br/>
            <label>���������</label>
            <textarea name="description" id="description" rows="3" cols="20" required></textarea>
            <br/>
            <label>ѡ�����ԣ�</label>
            <select name="language" id="language" required>
                <option value="1">English</option>
                <option value="2">Italian</option>
                <option value="3">Japanese</option>
                <option value="4">Mandarin</option>
                <option value="5">French</option>
                <option value="6">German</option>
            </select><br/>
            <button type="reset">����</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit">�ύ</button>
        </form>
        <%
            String err=(String)request.getSession().getAttribute("err");
            if(err!=null && !err.isEmpty()){
        %>
                <div style="color:red;"><%=err %></div>
    <%          request.getSession().removeAttribute("err");
            }
        } else{
            out.print("�㻹û�е�¼�������Զ�������¼ҳ��");
            response.setHeader("refresh", "2;url="+path+"/login.jsp");
        }
    %>
</div>
</body>
</html>
