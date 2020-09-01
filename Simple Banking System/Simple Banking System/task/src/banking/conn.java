package banking;

import java.sql.*;

public class conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void Conn(String url) throws ClassNotFoundException, SQLException{
        conn=null;
        Class.forName("org.sqlite.JDBC");
        conn= DriverManager.getConnection(url);
    }

    public static void CreateDB() throws ClassNotFoundException,SQLException{
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'card' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'number' TEXT, 'pin' TEXT, balance INTEGER DEFAULT 0);");
    }
    public static void WriteDB(String number,String pin,int balance) throws SQLException{
        statmt.execute("INSERT INTO 'card' ('number','pin','balance') VALUES('"+number+"', '"+pin+"' ,'"+balance+"');");
    }

    public static boolean ReadDB(String number,String pin) throws ClassNotFoundException,SQLException{
        resSet = statmt.executeQuery("SELECT *FROM card WHERE number = '"+number+"' AND pin='"+pin+"'");
        if (resSet.next()){
            System.out.println("You have successfully logged in!");
            return true;
        }else
     {
            System.out.println("Wrong card number or PIN!");
            return false;
        }
    }
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

    }
}
