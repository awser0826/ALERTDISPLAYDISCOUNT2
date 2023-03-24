package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class mydiscount extends AppCompatActivity {
Button details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mydiscount);
        details=findViewById(R.id.details1);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendiscount_message();
            }
        });
    }
    private void opendiscount_message(){
        Intent intent=new Intent(this,discount_message.class);
        startActivity(intent);
    }
}