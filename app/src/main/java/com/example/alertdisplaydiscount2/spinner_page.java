package com.example.alertdisplaydiscount2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class spinner_page extends AppCompatActivity {



    private Button btn1;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spinner_page);

        btn1 = findViewById(R.id.btn1);
        tv1 = findViewById(R.id.tv1);





        final Random random = new Random();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String randomNumber = String.valueOf(random.nextInt(9) % (9 - 1 + 1) + 1);

                tv1.setText(randomNumber);
            }


        });


    }


}