package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 徐 on 2018/8/13.
 */
public class CustomerDAO {
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

    public boolean findCustomer(String first_name){
        conn=getConnectionn();
        try {
            pStat =conn.prepareStatement("select * from customer where first_name=?");
            pStat.setString(1,first_name);
            rs=pStat.executeQuery();
            if( rs.next() )
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        } finally{
            close();
        }
    }
}
