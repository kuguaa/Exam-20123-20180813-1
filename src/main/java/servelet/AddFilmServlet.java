package servelet;

import bean.Film;
import dao.FilmDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 徐 on 2018/8/13.
 */
@WebServlet(name = "AddFilmServlet")
public class AddFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        byte[] bytes1=title.getBytes("ISO-8859-1");
        title=new String(bytes1);
        String description=request.getParameter("description");
        byte[] bytes2 = description.getBytes("ISO-8859-1");
        description = new String(bytes2);
        int language=Integer.parseInt(request.getParameter("language"));
        FilmDAO filmDAO=new FilmDAO();
        if(!filmDAO.isTitleExistsforInsert(title)){
            Film film=new Film();
            film.setTitle(title);
            film.setDescription(description);
            film.setLanguage(language);
            Boolean res = filmDAO.addFilm(film);
            if(res){
                response.sendRedirect("showfilms.jsp");
            }else{
                request.getSession().setAttribute("err","添加失败");
                response.sendRedirect("add.jsp");
            }
        }else {
            request.getSession().setAttribute("err","添加失败，电影名已存在");
            response.sendRedirect("add.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
