package com.simformsolutions.awesomeanimations;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FoodItem implements Parcelable {

    private String transitionName;
    private String title;
    private String description;
    private String chefName;
    private int hearderImageId;
    private int foodTypeIconId;
    private List<String> ingredientList;

    public FoodItem(String transitionName, String title, String description, String chefName, int hearderImageId, int foodTypeIconId, List<String> ingredientList) {
        this.transitionName = transitionName;
        this.title = title;
        this.description = description;
        this.chefName = chefName;
        this.hearderImageId = hearderImageId;
        this.foodTypeIconId = foodTypeIconId;
        this.ingredientList = ingredientList;
    }

    protected FoodItem(Parcel in) {
        transitionName = in.readString();
        title = in.readString();
        description = in.readString();
        chefName = in.readString();
        hearderImageId = in.readInt();
        foodTypeIconId = in.readInt();
        ingredientList = in.createStringArrayList();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public String getTransitionName() {
        return transitionName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getChefName() {
        return chefName;
    }

    public int getHearderImageId() {
        return hearderImageId;
    }

    public int getFoodTypeIconId() {
        return foodTypeIconId;
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(transitionName);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(chefName);
        dest.writeInt(hearderImageId);
        dest.writeInt(foodTypeIconId);
        dest.writeStringList(ingredientList);
    }
}
