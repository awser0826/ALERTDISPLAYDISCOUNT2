package com.example.alertdisplaydiscount2.listener;

import com.example.alertdisplaydiscount2.model.CartModel;
import com.example.alertdisplaydiscount2.model.FurnitureModel;

import java.util.List;

public interface ICartLoadListener {
    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);

}
