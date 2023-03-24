package com.example.alertdisplaydiscount2.listener;

import com.example.alertdisplaydiscount2.model.FurnitureModel;

import java.util.List;

public interface IFurnitureLoadListener {
    void onFurnitureLoadSuccess(List<FurnitureModel> furnitureModelList);
    void onFurnitureLoadFailed(String message);

}
