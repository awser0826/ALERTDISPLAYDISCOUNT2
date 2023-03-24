package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alertdisplaydiscount2.Connection.ConnectionClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class changingpassword extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button confirmchange,memberservice;
    EditText usernameforchangepassword,oldpassword,newpassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Connection con;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_changingpassword);

        //usernameforchangepassword=findViewById(R.id.usernameforchangepassword);
        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword);
        confirmchange=findViewById(R.id.confirmchangepassword);
        back = findViewById(R.id.back);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        confirmchange.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new changingpassword.checklogin().execute("");
            }
        });

        back.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        memberservice=findViewById(R.id.memberservice);
        memberservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmemberservice();
            }
        });
    }

    public class checklogin extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;
        String username = sharedPreferences.getString(getString(R.string.username), null);

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            if (newpassword.getText().toString().length() == 0) {
                newpassword.setError("新密碼不得為空!");
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            con = connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
            if(con == null||newpassword.getText().toString().length() == 0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(changingpassword.this,"連線錯誤或新密碼未填寫",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {
                try {
                    String sql = "SELECT * FROM 會員中心 WHERE username = '" + username + "' AND password = '" + oldpassword.getText() + "' ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(changingpassword.this, "修改密碼成功", Toast.LENGTH_LONG).show();

                            }
                        });
                        z = "Success";

                        String sqls = "UPDATE 會員中心 SET password='" + newpassword.getText() + "' WHERE username = '" + username + "'";
                        stmt = con.createStatement();
                        stmt.executeUpdate(sqls);

                        finish();


                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(changingpassword.this, "輸入的密碼錯誤 無法修改", Toast.LENGTH_LONG).show();
                            }
                        });

                        usernameforchangepassword.setText("");
                        oldpassword.setText("");
                        newpassword.setText("");
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
    private void openmemberservice(){
        Intent intent=new Intent(this,support.class);
        startActivity(intent);
    }
}