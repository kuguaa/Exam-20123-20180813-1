package servelet;

import dao.CustomerDAO;

import java.io.IOException;

/**
 * Created by 徐 on 2018/8/13.
 */
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String first_name=request.getParameter("first_name");
        String path = request.getContextPath();
        CustomerDAO customerdao=new CustomerDAO();
        boolean flag=customerdao.findCustomer(first_name);
        if( flag ) {
            request.getSession().setAttribute("first_name", first_name);
            response.sendRedirect(path+"/showfilms.jsp");
        } else{
            request.getSession().setAttribute("err", "用户名不正确！");
            response.sendRedirect(path+"/login.jsp");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
