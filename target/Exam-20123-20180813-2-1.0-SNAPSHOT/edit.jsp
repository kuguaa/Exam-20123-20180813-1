<%--
  Created by IntelliJ IDEA.
  User: ��
  Date: 2018/8/13
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="GBK" language="java" %>
<%@ page import="dao.FilmDAO" %>
<%@ page import="bean.Film" %>

<html>
<head>
    <title>�༭��Ӱ</title>
    <script src="scripts/jquery-3.2.1.js" language="JavaScript"></script>
</head>
<body>
<div align="center">

    <%String path = request.getContextPath();
    String first_name=(String)request.getSession().getAttribute("first_name");
    if(first_name!=null) {

        String id=request.getParameter("id");
        if(id!=null){
            FilmDAO fd=new FilmDAO();
            Film film=new Film();
            film=fd.selectFilmById(id);%>
            <form action="UpdateServlet" method="post">

                <input name="film_id" hidden="hidden" value="<%=id%>"/>
                <label>��Ӱ���ƣ�</label>
                <input type="text" name="title" id="title" value="<%=film.getTitle()%>" required><br/>
                <label>���������</label>
                <textarea name="description" id="description" rows="3" cols="20" required></textarea>
                <span id="up_des" hidden="hidden"><%=film.getDescription()%></span>
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
                <span id="up_lan" hidden="hidden"><%=film.getLanguage()%></span>
                <button><a href="showfilms.jsp">ȡ��</a></button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit">�ύ</button>

            </form>
        <%
            String err=(String)request.getSession().getAttribute("err");
            if(err!=null){
        %>
                <div style="color:red;"><%=err %></div>
        <%      request.getSession().removeAttribute("err");
            }
        }else{%>
                ��ѡ��һ����Ӱ  <a href="showfilms.jsp">��Ӱ�б�</a>"
          <%  }
        }else{%>
            �㻹û�е�¼�������Զ�������¼ҳ��
          <%  response.setHeader("refresh", "2;url="+path+"/login.jsp");
        }%>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        var x = document.getElementById("up_des").innerHTML;
        $("#description").val(x);
        var y = document.getElementById("up_lan").innerHTML;
        $("#language").val(y);

    });
</script>
</body>
</html>