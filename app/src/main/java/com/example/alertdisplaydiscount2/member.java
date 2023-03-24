package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alertdisplaydiscount2.Connection.ConnectionClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class member extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button memberregister, forgetpassword, memberservice, login;
    EditText account, password;
    //CheckBox checkbox;
    Connection con;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    //private static final String SHARED_PREF_NAME="mypref";
    //private static final String KEY_USERNAME="account";
    //private static final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_member);

        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        //checkbox = findViewById(R.id.checkBox);
        login = findViewById(R.id.member_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sharedPreferences= this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        editor = sharedPreferences.edit();

        checkSharedPreferences();


        login.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SharedPreferences.Editor editor=sharedPreferences.edit();
                //editor.putString(KEY_USERNAME,account.getText().toString());
                //editor.putString(KEY_PASSWORD,password.getText().toString());
                //editor.apply();

                //if(checkbox.isChecked()){
                //editor.putString(getString(R.string.checkbox),"True");
                //editor.commit();




                //}else {
                //editor.putString(getString(R.string.checkbox),"False");
                //editor.commit();

                //editor.putString(getString(R.string.username),"");
                //editor.commit();

                //editor.putString(getString(R.string.password),"");
                //editor.commit();
                //  }
                new member.checklogin().execute("");
            }
        });

        //String username=sharedPreferences.getString(KEY_USERNAME,null);
        //if(username !=null){

        //Intent intent = new Intent(member.this, membercenter.class);
        //startActivity(intent);
        // }

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

        memberregister=findViewById(R.id.member_register);
        memberregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmemberregister();
            }
        });

        forgetpassword=findViewById(R.id.forgetpassword);
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openforgetpassword();
            }
        });

        memberservice=findViewById(R.id.memberservice);
        memberservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmemberservice();
            }
        });



    }
    private void checkSharedPreferences() {
        //String checkboxs = sharedPreferences.getString(getString(R.string.checkbox), "False");
        String username = sharedPreferences.getString(getString(R.string.username), null);
        String passwords = sharedPreferences.getString(getString(R.string.password), null);

        account.setText(username);
        password.setText(passwords);

        //if(checkboxs.equals("True")){
        //checkbox.setChecked(true);
        //}
        //else{
        //checkbox.setChecked(false);
        //}

        if(username !=null){
            Intent intent = new
                    Intent(member.this,membercenter.class);
            startActivity(intent);
        }

    }
    public class checklogin extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {


        }

        @Override
        protected String doInBackground(String... strings) {
            con = connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
            if(con == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(member.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {
                try {

                    String sql = "SELECT * FROM 會員中心 WHERE username = '" + account.getText() + "' AND password = '" + password.getText() + "' ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String username=account.getText().toString();

                                editor.putString(getString(R.string.username),username);
                                editor.commit();



                                String passwords=password.getText().toString();

                                editor.putString(getString(R.string.password), passwords);
                                editor.commit();

                                Toast.makeText(member.this, "登入成功", Toast.LENGTH_LONG).show();
                            }
                        });
                        z = "Success";

                        Intent intent = new Intent(member.this, membercenter.class);
                        startActivity(intent);
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(member.this, "登入失敗 請確認帳號和密碼無誤", Toast.LENGTH_LONG).show();
                            }
                        });

                        account.setText("");
                        password.setText("");
                    }
                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }
            }
            return z;
        }
    }





    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        }catch (Exception e){
            Log.e("SQL Connection Error : ", e.getMessage());
        }

        return connection;
    }
    private void openmemberregister(){
        Intent intent=new Intent(this, memberregister.class);
        startActivity(intent);
    }
    private void openforgetpassword(){
        Intent intent=new Intent(this,forgetpassword.class);
        startActivity(intent);
    }
    private void openmemberservice(){
        Intent intent=new Intent(this,support.class);
        startActivity(intent);
    }
}