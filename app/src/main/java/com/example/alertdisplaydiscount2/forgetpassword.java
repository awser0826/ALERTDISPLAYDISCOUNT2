package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class forgetpassword extends AppCompatActivity {
    Button forgetpassword_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgetpassword);

        forgetpassword_service=findViewById(R.id.forgetpassword_service);
        forgetpassword_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openservice();
            }
        });
    }
    private void openservice(){
        Intent intent=new Intent(this,support.class);
        startActivity(intent);
    }
}