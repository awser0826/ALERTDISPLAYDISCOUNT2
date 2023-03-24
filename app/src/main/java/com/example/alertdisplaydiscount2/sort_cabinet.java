package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.alertdisplaydiscount2.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sort_cabinet extends AppCompatActivity {
    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sort_cabinet);
    }
    public List<Map<String,String>> getlist()
    {
        List<Map<String,String>> data=null;
        data = new ArrayList<Map<String,String>>();
        try {
            ConnectionClass connectionHelper=new ConnectionClass();
            connect=connectionHelper.conclass();
            if(connect ==null)
            {
                ConnectionResult="檢查網路使用權!";
            }
            else {
                String qu ="select * from 商品資訊 where CCATEGORY like'%shelf%'";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(qu);
                while (resultSet.next())
                {
                    Map<String,String>dtname=new HashMap<String,String>();
                    dtname.put("CNum",resultSet.getString("CNUMBER"));
                    dtname.put("CName",resultSet.getString("CNAME"));
                    dtname.put("Det",resultSet.getString("CDETAILS"));
                    dtname.put("CMoney",resultSet.getString("CMONEY"));
                    dtname.put("CMany",resultSet.getString("CMANY"));
                    dtname.put("CImage",resultSet.getString("CIMAGE"));
                    data.add(dtname);
                }

                ConnectionResult="Success";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception throwables) {
            isSuccess=false;
            ConnectionResult=throwables.getMessage();
        }
        return data;
    }
}