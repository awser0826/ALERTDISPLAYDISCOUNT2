package com.example.alertdisplaydiscount2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Space;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.alertdisplaydiscount2.adapter.MyFurnitureAdapter;
import com.example.alertdisplaydiscount2.eventbus.MyUpdateCartEvent;
import com.example.alertdisplaydiscount2.listener.ICartLoadListener;
import com.example.alertdisplaydiscount2.listener.IFurnitureLoadListener;
import com.example.alertdisplaydiscount2.model.CartModel;
import com.example.alertdisplaydiscount2.model.FurnitureModel;
import com.example.alertdisplaydiscount2.utils.SpaceItemDecoration;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class shoppingcart extends AppCompatActivity implements IFurnitureLoadListener, ICartLoadListener {

    @BindView(R.id.recycler_furniture)
    RecyclerView recyclerFurniture;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;

    IFurnitureLoadListener furnitureLoadListener;
    ICartLoadListener cartLoadListener;

    @Override
            protected void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class));
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
            EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
            public void onUpdateCart(MyUpdateCartEvent event)
    {
        countCartItem();
    }

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_shoppingcart);


        init();
        loadDrinkFromFirebase();
        countCartItem();

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_shoppingcar);

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


    }

    private void loadDrinkFromFirebase() {
        List<FurnitureModel> furnitureModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            for(DataSnapshot furnitureSnapshot:snapshot.getChildren())
                            {
                                FurnitureModel furnitureModel = furnitureSnapshot.getValue(FurnitureModel.class);
                                furnitureModel.setKey(furnitureSnapshot.getKey());
                                furnitureModels.add(furnitureModel);
                            }
                            furnitureLoadListener.onFurnitureLoadSuccess(furnitureModels);
                        }
                        else
                            furnitureLoadListener.onFurnitureLoadFailed("載入失敗");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
furnitureLoadListener.onFurnitureLoadFailed(error.getMessage());
                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);

        furnitureLoadListener = this;
        cartLoadListener=this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerFurniture.setLayoutManager(gridLayoutManager);
        recyclerFurniture.addItemDecoration(new SpaceItemDecoration());

        btnCart.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));
    }

    @Override
    public void onFurnitureLoadSuccess(List<FurnitureModel> furnitureModelList) {
        MyFurnitureAdapter adapter = new MyFurnitureAdapter(this,furnitureModelList,cartLoadListener);
        recyclerFurniture.setAdapter(adapter);
    }

    @Override
    public void onFurnitureLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

        int cartSum = 0;
        for (CartModel cartModel: cartModelList)
            cartSum += cartModel.getQuantity();
        badge.setNumber(cartSum);
    }

    @Override
    public void onCartLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
    @Override
    protected void onResume(){
        super.onResume();
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot:snapshot.getChildren())
                        {
                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);
                        }
                        cartLoadListener.onCartLoadSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartLoadFailed(error.getMessage());
                    }
                });
    }
}