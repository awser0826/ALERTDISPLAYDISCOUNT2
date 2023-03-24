package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class support extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_support);

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



        back = findViewById(R.id.back);
        back.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }
}