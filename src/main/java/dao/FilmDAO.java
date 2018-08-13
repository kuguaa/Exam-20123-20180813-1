package dao;

import bean.Film;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by 徐 on 2018/8/13.
 */
public class FilmDAO {
    public static final String DRIVER="org.gjt.mm.mysql.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/sakila";
    public static final String DBUSER="root";
    public static final String DBPASS="root";
    private Connection conn=null;
    private PreparedStatement pStat=null;
    private ResultSet rs=null;
    public Connection getConnectionn(){
        try{
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(DBURL,DBUSER,DBPASS);
        }catch(Exception e){
            return null;
        }
    }

    public void close(){
        try{
            if( rs!=null )
                rs.close();
            if( pStat!=null )
                pStat.close();
            if( conn!=null )
                conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isTitleExistsforInsert(String title) {
        conn=getConnectionn();
        try {
            pStat =conn.prepareStatement("select title from Film where title=?");
            pStat.setString(1, title);
            rs=pStat.executeQuery();
            if( rs.next() )
                return true;
            else
                return false;
        }catch (Exception e) {
            return false;
        } finally{
            close();
        }
    }

    public boolean isTitleExistsforUpdate(int film_id, String newTitle) {
        conn=getConnectionn();
        try {
            pStat =conn.prepareStatement("select title from Film where title=?");
            pStat.setString(1, newTitle);
            rs=pStat.executeQuery();
            if(rs.next()){
                pStat =conn.prepareStatement("select title from Film where film_id=?");
                pStat.setInt(1, film_id);
                rs=pStat.executeQuery();
                String oldTitle="";
                while(rs.next()){
                    oldTitle=rs.getString("title");
                }
                if(oldTitle.equals(newTitle)){
                    return false;
                }
                else{
                    return true;
                }

            }
            else
                return false;
        }catch (Exception e) {
            return false;
        } finally{
            close();
        }
    }

    public boolean addFilm(Film film) {
        conn=getConnectionn();
        try {
            pStat=conn.prepareStatement("insert into Film(title,description,language_id) values(?,?,?)");
            pStat.setString(1, film.getTitle());
            pStat.setString(2, film.getDescription());
            pStat.setInt(3, film.getLanguage());
            int cnt=pStat.executeUpdate();
            if(cnt>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        } finally{
            close();
        }
    }

    public boolean updateFilm(Film film) {
        conn=getConnectionn();
        try {
            pStat=conn.prepareStatement("update Film set title=?,description=?,language_id=? where film_id=?");
            pStat.setString(1, film.getTitle());
            pStat.setString(2, film.getDescription());
            pStat.setInt(3, film.getLanguage());
            pStat.setInt(4, film.getFilm_id());
            int cnt=pStat.executeUpdate();
            if(cnt>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        } finally{
            close();
        }
    }

    public Film selectFilmById(String film_id){
        conn=getConnectionn();
        Film Film=new Film();
        try {
            pStat=conn.prepareStatement("select film_id,title,description,f.language_id,l.name from FILM as f,LANGUAGE l where film_id=? and f.language_id=l.language_id");
            pStat.setString(1, film_id);
            rs=pStat.executeQuery();
            while(rs.next()){
                Film.setFilm_id(Integer.valueOf(rs.getString("film_id")));
                Film.setTitle(rs.getString("title"));
                Film.setDescription(rs.getString("description"));
                Film.setLanguage(rs.getInt("language_id"));
                Film.setLanguage_name(rs.getString("name"));
            }

        } catch (Exception e) {

        } finally{
            close();
        }
        return Film;
    }

    public boolean deleteFilmById(int film_id){
        conn=getConnectionn();
        try {
            pStat=conn.prepareStatement("delete from Film where film_id=?");
            pStat.setInt(1, film_id);
            int cnt=pStat.executeUpdate();
            if(cnt>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        } finally{
            close();
        }
    }

    public ArrayList<Film> showFilms(){
        conn=getConnectionn();
        ArrayList<Film> Films=new ArrayList<Film>();
        try {
            pStat=conn.prepareStatement("select film_id,title,description,l.name from FILM as f,LANGUAGE l where f.language_id=l.language_id order by film_id desc");
            rs=pStat.executeQuery();
            while(rs.next()){
                Film Film=new Film();
                Film.setFilm_id(rs.getInt("film_id"));
                Film.setTitle(rs.getString("title"));
                Film.setDescription(rs.getString("description"));
                Film.setLanguage_name(rs.getString("name"));
                Films.add(Film);
            }

        } catch (Exception e) {

        } finally{
            close();
        }
        return Films;
    }

}
