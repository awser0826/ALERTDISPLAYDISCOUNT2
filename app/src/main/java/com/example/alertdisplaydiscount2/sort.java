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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Map;

public class sort extends AppCompatActivity {
    SimpleAdapter ad;
    ListView list_view ;
BottomNavigationView bottomNavigationView;
ImageButton table,bed,sofa,cabinet,chair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sort);
        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_sort);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sort:

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
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
        list_view = (ListView) findViewById(R.id.listview1);
        table=findViewById(R.id.sort_table);
        bed=findViewById(R.id.sort_bed);
        sofa=findViewById(R.id.sort_sofa);
        cabinet=findViewById(R.id.sort_cabinet);
        chair=findViewById(R.id.sort_chair);

        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String, String>> MyDataList = null;
                sort_table MyData = new sort_table();
                MyDataList = MyData.getlist();

                String[] Fromw = {"CNum", "CName", "Det", "CMoney", "CMany", "CImage"};
                int[] Tow = {R.id.CNum, R.id.CName, R.id.Det, R.id.CMoney, R.id.CMany, R.id.CImage};
                ad = new SimpleAdapter(sort.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
                list_view.setAdapter(ad);
            }
        });
        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String, String>> MyDataList = null;
                sort_bed MyData = new sort_bed();
                MyDataList = MyData.getlist();

                String[] Fromw = {"CNum", "CName", "Det", "CMoney", "CMany", "CImage"};
                int[] Tow = {R.id.CNum, R.id.CName, R.id.Det, R.id.CMoney, R.id.CMany, R.id.CImage};
                ad = new SimpleAdapter(sort.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
                list_view.setAdapter(ad);
            }
        });
        sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String, String>> MyDataList = null;
                sort_sofa MyData = new sort_sofa();
                MyDataList = MyData.getlist();

                String[] Fromw = {"CNum", "CName", "Det", "CMoney", "CMany", "CImage"};
                int[] Tow = {R.id.CNum, R.id.CName, R.id.Det, R.id.CMoney, R.id.CMany, R.id.CImage};
                ad = new SimpleAdapter(sort.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
                list_view.setAdapter(ad);
            }
        });
        cabinet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String, String>> MyDataList = null;
                sort_cabinet MyData = new sort_cabinet();
                MyDataList = MyData.getlist();

                String[] Fromw = {"CNum", "CName", "Det", "CMoney", "CMany", "CImage"};
                int[] Tow = {R.id.CNum, R.id.CName, R.id.Det, R.id.CMoney, R.id.CMany, R.id.CImage};
                ad = new SimpleAdapter(sort.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
                list_view.setAdapter(ad);
            }
        });
        chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String, String>> MyDataList = null;
                sort_chair MyData = new sort_chair();
                MyDataList = MyData.getlist();

                String[] Fromw = {"CNum", "CName", "Det", "CMoney", "CMany", "CImage"};
                int[] Tow = {R.id.CNum, R.id.CName, R.id.Det, R.id.CMoney, R.id.CMany, R.id.CImage};
                ad = new SimpleAdapter(sort.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
                list_view.setAdapter(ad);
            }
        });
    }











    private void opensort_table(){
        Intent intent=new Intent(this,sort_table.class);
        startActivity(intent);
    }
    private void opensort_bed(){
        Intent intent=new Intent(this,sort_bed.class);
        startActivity(intent);
    }
    private void opensort_sofa(){
        Intent intent=new Intent(this,sort_sofa.class);
        startActivity(intent);
    }
    private void opensort_cabinet(){
        Intent intent=new Intent(this,sort_cabinet.class);
        startActivity(intent);
    }
    private void opensort_chair(){
        Intent intent=new Intent(this,sort_chair.class);
        startActivity(intent);
    }
}