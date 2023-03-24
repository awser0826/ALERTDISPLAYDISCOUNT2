package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ship_mydiscount extends AppCompatActivity {
Button shipdiscount_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ship_mydiscount);

        shipdiscount_details=findViewById(R.id.shipdiscount_details1);
        shipdiscount_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshipdiscount_shop();
            }
        });
    }
    private void openshipdiscount_shop(){
        Intent intent=new Intent(this,discount_use.class);
        startActivity(intent);
    }
}