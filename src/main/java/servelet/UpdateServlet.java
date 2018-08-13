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
@WebServlet(name = "UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int film_id=Integer.parseInt(request.getParameter("film_id"));
        String title=request.getParameter("title");
        byte[] b1 = title.getBytes("ISO-8859-1");
        title = new String(b1);
        String description=request.getParameter("description");
        byte[] b2 = description.getBytes("ISO-8859-1");
        description = new String(b2);
        int language=Integer.parseInt(request.getParameter("language"));
        FilmDAO filmDAO=new FilmDAO();
        if(!filmDAO.isTitleExistsforUpdate(film_id,title)){
            Film film=new Film();
            film.setFilm_id(film_id);
            film.setTitle(title);
            film.setDescription(description);
            film.setLanguage(language);
            Boolean res = filmDAO.updateFilm(film);
            if(res){
                response.sendRedirect("showfilms.jsp");
            }else{
                request.getSession().setAttribute("err","修改失败");
                response.sendRedirect("edit.jsp?id="+film_id);
            }
        }else {
            request.getSession().setAttribute("err","修改失败，电影名已存在");
            response.sendRedirect("edit.jsp?id="+film_id);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
