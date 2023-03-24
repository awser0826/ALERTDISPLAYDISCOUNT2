package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class home extends AppCompatActivity {


    SliderView sliderView;
    int[] images = {R.drawable.aa,
            R.drawable.ab,
            R.drawable.ac,
            R.drawable.ad
    };


    BottomNavigationView bottomNavigationView;
    ImageButton restaurant,combine,delivery,camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home);

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);



        sliderView=findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter=new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



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
                        startActivity(new Intent(getApplicationContext(),member.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        return true;
                }

                return false;
            }
        });
        restaurant=findViewById(R.id.home_restaurant);

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openrestaurant();
            }
        });

        combine=findViewById(R.id.home_combine);
        combine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openassembly();
            }
        });

        delivery=findViewById(R.id.home_deliver);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendelivery();
            }
        });

        camera=findViewById(R.id.home_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencamera();
            }
        });
    }
    private void openrestaurant(){
        Intent intent=new Intent(this, restaurant.class);
        startActivity(intent);
    }
    private void openassembly(){
        Intent intent=new Intent(this,assembly.class);
        startActivity(intent);
    }
    private void opendelivery(){
        Intent intent=new Intent(this, com.example.alertdisplaydiscount2.delivery.class);
        startActivity(intent);
    }
    private void opencamera(){
        Intent intent=new Intent(this, com.example.alertdisplaydiscount2.cameraload.class);
        startActivity(intent);
    }
}