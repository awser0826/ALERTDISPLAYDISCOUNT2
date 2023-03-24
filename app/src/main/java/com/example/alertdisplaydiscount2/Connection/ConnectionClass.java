package com.example.alertdisplaydiscount2.Connection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public static String ip ="140.137.61.144"; // SQL Server IP 地址
    public static String un ="student"; // SQL Server 用戶名
    public static String pass="project@110"; // SQL Server 密碼
    public static String db ="ADD"; // SQL Server 數據庫

    @SuppressLint("NewApi")
    public Connection conclass()
    {
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection =null;
        String ConnectionURL=null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://" + ip +";databaseName="+ db + ";user=" + un + ";password=" + pass + ";";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException ex)
        {
            Log.e("error here 1 : ", ex.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
