package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class discount_use extends AppCompatActivity {
Button use;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_discount_use);

        use=findViewById(R.id.use);
        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnshoppingcart();
            }
        });
    }
    private void returnshoppingcart(){
        Intent intent=new Intent(this,shoppingcart.class);
        startActivity(intent);
    }
}