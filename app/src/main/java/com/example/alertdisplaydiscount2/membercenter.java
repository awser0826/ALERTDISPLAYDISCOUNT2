package com.example.alertdisplaydiscount2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alertdisplaydiscount2.Connection.ConnectionClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class membercenter extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView textView_username;
    Button changepassword,logout,deleteaccount,setting,support,privacy,ordertraking,memberinformation,address;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;


    //private static final String SHARED_PREF_NAME="mypref";
    //private static final String KEY_USERNAME="account";
    //private static final String KEY_PASSWORD="password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_membercenter);

        textView_username =findViewById(R.id.textView_username);
        logout = findViewById(R.id.logout);
        support=findViewById(R.id.support);
        setting=findViewById(R.id.setting);
        privacy=findViewById(R.id.privacy);
        address=findViewById(R.id.address);
        memberinformation=findViewById(R.id.memberinformation);
        ordertraking=findViewById(R.id.ordertraking);
        deleteaccount=findViewById(R.id.deleteaccount);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        checkSharedPreferences();
        // sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        // String username=sharedPreferences.getString(KEY_USERNAME,null);
        //String password=sharedPreferences.getString(KEY_PASSWORD,null);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Toast.makeText(membercenter.this,"登出成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(membercenter.this, member.class);
                startActivity(intent);
            }
        });
        deleteaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendeleteaccount();
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensupport();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensetting();
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprivacy();
            }
        });
        ordertraking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openordertraking();
            }
        });
        memberinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmemberinformation();
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddress();
            }
        });

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_member);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sort:
                        startActivity(new Intent(getApplicationContext(),sort.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_discount:
                        startActivity(new Intent(getApplicationContext(),discount.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_shoppingcar:
                        startActivity(new Intent(getApplicationContext(),shoppingcart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_member:

                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });


        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_member);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sort:
                        startActivity(new Intent(getApplicationContext(),sort.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_discount:
                        startActivity(new Intent(getApplicationContext(),discount.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_shoppingcar:
                        startActivity(new Intent(getApplicationContext(),shoppingcart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_member:

                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        changepassword=findViewById(R.id.button_changepassword);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openchangepassword();
            }
        });
    }
    private void openchangepassword(){
        Intent intent=new Intent(this, changingpassword.class);
        startActivity(intent);
    }
    private void opendeleteaccount(){
        Intent intent=new Intent(this, deleteaccount.class);
        startActivity(intent);
    }
    private void opensupport(){
        Intent intent=new Intent(this, support.class);
        startActivity(intent);
    }
    private void opensetting(){
        Intent intent=new Intent(this, setting.class);
        startActivity(intent);
    }
    private void openprivacy(){
        Intent intent=new Intent(this, privacy.class);
        startActivity(intent);
    }
    private void openordertraking(){
        Intent intent=new Intent(this, ordertraking.class);
        startActivity(intent);
    }
    private void openmemberinformation(){
        Intent intent=new Intent(this, memberinformation.class);
        startActivity(intent);
    }
    private void openaddress(){
        Intent intent=new Intent(this, address.class);
        startActivity(intent);
    }
    private void checkSharedPreferences() {
        //String checkboxs = sharedPreferences.getString(getString(R.string.checkbox), "False");
        String username = sharedPreferences.getString(getString(R.string.username), null);
        //String passwords = sharedPreferences.getString(getString(R.string.password), null);

        //account.setText(username);
        //password.setText(passwords);

        {
            List<Map<String, String>> data = null;
            data = new ArrayList<Map<String, String>>();
            try {
                ConnectionClass connectionHelper = new ConnectionClass();
                connect = connectionHelper.conclass();
                if (connect == null) {
                    ConnectionResult = "檢查網路使用權!";
                } else {

                    String qu = "SELECT * FROM 會員中心 WHERE username = '" + username + "'  ";
                    Statement statement = connect.createStatement();
                    ResultSet resultSet = statement.executeQuery(qu);
                    while (resultSet.next()) {
                        Map<String, String> dtname = new HashMap<String, String>();

                        textView_username.setText("歡迎回來"+resultSet.getString("name"));


                        data.add(dtname);
                    }


                    ConnectionResult = "Success";
                    isSuccess = true;
                    connect.close();
                }
            } catch (Exception throwables) {
                isSuccess = false;
                ConnectionResult = throwables.getMessage();
            }

        }
    }
}