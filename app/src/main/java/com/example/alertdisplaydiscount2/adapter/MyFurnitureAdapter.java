package com.example.alertdisplaydiscount2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alertdisplaydiscount2.R;
import com.example.alertdisplaydiscount2.eventbus.MyUpdateCartEvent;
import com.example.alertdisplaydiscount2.listener.ICartLoadListener;
import com.example.alertdisplaydiscount2.listener.IRecyclerViewClickListener;
import com.example.alertdisplaydiscount2.model.CartModel;
import com.example.alertdisplaydiscount2.model.FurnitureModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFurnitureAdapter extends RecyclerView.Adapter<MyFurnitureAdapter.MyFurnitureViewHolder>{

    private Context context;
    private List<FurnitureModel> furnitureModelList;
    private ICartLoadListener iCartLoadListener;

    public MyFurnitureAdapter(Context context, List<FurnitureModel> furnitureModelList, ICartLoadListener iCartLoadListener) {
        this.context = context;
        this.furnitureModelList = furnitureModelList;
        this.iCartLoadListener = iCartLoadListener;
    }

    @NonNull

    @Override
    public MyFurnitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFurnitureViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_furniture_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFurnitureAdapter.MyFurnitureViewHolder holder, int position) {
        Glide.with(context)
                .load(furnitureModelList.get(position).getImage())
                .into(holder.imageView100);
        holder.txtPrice1.setText(new StringBuilder("$").append(furnitureModelList.get(position).getPrice()));
        holder.txtName1.setText(new StringBuilder().append(furnitureModelList.get(position).getName()));

        holder.setListener((view, adapterPosition) -> {
            addToCart(furnitureModelList.get(position));
        });
    }

    private void addToCart(FurnitureModel furnitureModel) {
        DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID");

        userCart.child(furnitureModel.getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            CartModel cartModel = snapshot.getValue(CartModel.class);
                            cartModel.setQuantity(cartModel.getQuantity()+1);
                            Map<String,Object> updateData = new HashMap<>();
                            updateData.put("quantity",cartModel.getQuantity());
                            updateData.put("totalPrice",cartModel.getQuantity()*Float.parseFloat(cartModel.getPrice()));

                            userCart.child(furnitureModel.getKey())
                                    .updateChildren(updateData)
                                    .addOnSuccessListener(unused -> {
                                        iCartLoadListener.onCartLoadFailed("成功加入購物車");
                                    })
                            .addOnFailureListener(e -> iCartLoadListener.onCartLoadFailed(e.getMessage()));
                        }
                        else
                        {
                            CartModel cartModel = new CartModel();
                            cartModel.setName(furnitureModel.getName());
                            cartModel.setImage(furnitureModel.getImage());
                            cartModel.setKey(furnitureModel.getKey());
                            cartModel.setPrice(furnitureModel.getPrice());
                            cartModel.setQuantity(1);
                            cartModel.setTotalprice(Float.parseFloat(furnitureModel.getPrice()));

                            userCart.child(furnitureModel.getKey())
                                    .setValue(cartModel)
                                    .addOnSuccessListener(unused -> {
                                        iCartLoadListener.onCartLoadFailed("成功加入購物車");
                                    })
                                    .addOnFailureListener(e -> iCartLoadListener.onCartLoadFailed(e.getMessage()));
                        }
                        EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        iCartLoadListener.onCartLoadFailed(error.getMessage());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return furnitureModelList.size();
    }

    public class MyFurnitureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageView100)
        ImageView imageView100;
        @BindView(R.id.txtName1)
        TextView txtName1;
        @BindView(R.id.txtPrice1)
        TextView txtPrice1;

        IRecyclerViewClickListener listener;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        private Unbinder unbinder;
        public MyFurnitureViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onRecyclerClick(v,getAdapterPosition());
        }
    }
}
