package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.alertdisplaydiscount2.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class memberregister extends AppCompatActivity {
    boolean invalid = false;
Button memberrigester_service,signup,havemember;
EditText username,password,phone,email,name,birthday;
    TextView status;
    Connection con;
    Statement stmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_memberregister);

        memberrigester_service=findViewById(R.id.memberregister_service);
        memberrigester_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensupport();
            }
        });


        username=(EditText)findViewById(R.id.registeraccount);
        password=(EditText)findViewById(R.id.registerpassword);
        phone=(EditText)findViewById(R.id.phone);
        birthday = (EditText) findViewById(R.id.birthday);
        email = (EditText) findViewById(R.id.email);
        name=(EditText)findViewById(R.id.name);
        signup=findViewById(R.id.submit);
        havemember=findViewById(R.id.havemember);
        status = (TextView) findViewById(R.id.status);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new memberregister.registeruser().execute("");
            }
        });
        havemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),member.class);
                startActivity(intent);
            }
        });
    }
    private void opensupport(){
        Intent intent=new Intent(this,support.class);
        startActivity(intent);
    }


    private boolean validateEmailAddress (EditText email) {
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "電子郵件驗證成功", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "電子郵件格式錯誤",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }







    public class registeruser extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;





        @Override
        protected void onPreExecute() {

            status.setText("註冊中");
        }

        @Override
        protected void onPostExecute(String s) {


            EditText username = (EditText)findViewById(R.id.registeraccount);
            EditText password = (EditText)findViewById(R.id.registerpassword);
            EditText email = (EditText)findViewById(R.id.email);
            EditText name = (EditText)findViewById(R.id.name);
            EditText phone = (EditText)findViewById(R.id.phone);
            EditText birthday = (EditText)findViewById(R.id.birthday);


            if (username.getText().toString().length() == 0){
                new AlertDialog.Builder(memberregister.this)

                        .setTitle("帳號不得為空")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("取消", null).create()
                        .show();
                username.setError( "帳號不得為空!" );
            }
            else if (password.getText().toString().length() == 0){
                new AlertDialog.Builder(memberregister.this)

                        .setTitle("密碼不得為空")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("取消", null).create()
                        .show();
                password.setError( "密碼不得為空!" );
            }
            else if (email.getText().toString().length() == 0){
                new AlertDialog.Builder(memberregister.this)

                        .setTitle("信箱不得為空")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("取消", null).create()
                        .show();
                email.setError( "信箱不得為空!" );
            }
            else if (name.getText().toString().length() == 0){
                new AlertDialog.Builder(memberregister.this)

                        .setTitle("姓名不得為空")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("取消", null).create()
                        .show();
                name.setError( "姓名不得為空!" );
            }
            else if (phone.getText().toString().length() == 0){
                new AlertDialog.Builder(memberregister.this)

                        .setTitle("電話不得為空")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("取消", null).create()
                        .show();
                phone.setError( "電話不得為空!" );
            }
            else if (birthday.getText().toString().length() == 0) {

                new AlertDialog.Builder(memberregister.this)

                        .setTitle("生日不得為空")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("取消", null).create()
                        .show();

                birthday.setError("生日不得為空!");

            }

            else {
                status.setText("註冊成功");
                //name.setText("");
                //email.setText("");
                //password.setText("");
                //username.setText("");
                //birthday.setText("");
                //phone.setText("");

                new AlertDialog.Builder(memberregister.this)

                        .setTitle("已註冊成功")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getApplicationContext(),member.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", null).create()
                        .show();

            }


        }

        @Override
        protected String doInBackground(String... strings) {
            EditText username = (EditText)findViewById(R.id.registeraccount);
            EditText password = (EditText)findViewById(R.id.registerpassword);
            EditText email = (EditText)findViewById(R.id.email);
            EditText name = (EditText)findViewById(R.id.name);
            EditText phone = (EditText)findViewById(R.id.phone);
            EditText birthday = (EditText)findViewById(R.id.birthday);

            try {
                con = connectionClass(ConnectionClass.un.toString(), ConnectionClass.pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
                if (con == null) {
                    z = "Check Your Internet Connection";
                }
                else if (username.getText().toString().length() == 0){

                    username.setError( "帳號不得為空!" );
                }
                else if (password.getText().toString().length() == 0){

                    password.setError( "密碼不得為空!" );
                }
                else if (email.getText().toString().length() == 0){

                    email.setError( "信箱不得為空!" );
                }
                else if (name.getText().toString().length() == 0){

                    name.setError( "姓名不得為空!" );
                }
                else if (phone.getText().toString().length() == 0){

                    phone.setError( "電話不得為空!" );
                }
                else if (birthday.getText().toString().length() == 0){

                    birthday.setError( "生日不得為空!" );
                }
                else {
                    String sql = "INSERT INTO 會員中心 (name,email,password,username,birthday,phone) VALUES ('" + name.getText() + "','" + email.getText() + "','" + password.getText() + "','" + username.getText()+ "','" + birthday.getText() + "','" + phone.getText() + "')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }

            } catch (Exception e) {
                isSuccess = false;
                z = e.getMessage();
            }

            return z;
        }
    }
    @SuppressLint("NewApi")
    public Connection connectionClass (String user, String password, String database, String
            server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server + "/" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            Log.e("SQL Connection Error : ", e.getMessage());
        }

        return connection;
    }
}