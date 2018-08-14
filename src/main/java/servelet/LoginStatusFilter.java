package servelet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 徐 on 2018/8/14.
 */
public class LoginStatusFilter implements Filter {
    private FilterConfig filterConfig;
    private String status;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;

    }

    public void destroy() {
        this.filterConfig=null;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=gb2312");
        PrintWriter out = servletResponse.getWriter();
        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("err.jsp");
        HttpServletRequest request=(HttpServletRequest)servletRequest;

        status = (String)request.getSession().getAttribute("first_name");
        System.out.println(status);
        if(status==null){
            dispatcher.forward(servletRequest,servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
