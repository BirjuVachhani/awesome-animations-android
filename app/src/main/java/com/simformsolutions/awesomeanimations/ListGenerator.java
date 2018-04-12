package com.simformsolutions.awesomeanimations;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListGenerator {

    private String[] titles;
    private String[] desc;
    private String[] foodChefs;
    private int[] ingredientIds = {R.array.ingredients1, R.array.ingredients2, R.array.ingredients3,
            R.array.ingredients4, R.array.ingredients5, R.array.ingredients6, R.array.ingredients7,
            R.array.ingredients8, R.array.ingredients9, R.array.ingredients10, R.array.ingredients11,
            R.array.ingredients12, R.array.ingredients13};

    private int[] headerImageIds = {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4,
            R.drawable.item5, R.drawable.item6, R.drawable.item7, R.drawable.item8, R.drawable.item9,
            R.drawable.item10, R.drawable.item11, R.drawable.item12, R.drawable.item13};

    private int[] foodTypeIconIds = {R.drawable.item_icon1, R.drawable.item_icon2, R.drawable.item_icon3,
            R.drawable.item_icon4, R.drawable.item_icon5, R.drawable.item_icon6, R.drawable.item_icon7,
            R.drawable.item_icon8, R.drawable.item_icon9, R.drawable.item_icon10, R.drawable.item_icon11,
            R.drawable.item_icon12, R.drawable.item_icon13};

    private Context context;

    public ListGenerator(Context context) {
        this.context = context;
        titles = context.getResources().getStringArray(R.array.pesto_titles);
        desc = context.getResources().getStringArray(R.array.pesto_descriptions);
        foodChefs = context.getResources().getStringArray(R.array.food_chefs);
    }

    public List<FoodItem> generate() {
        List<FoodItem> mlist = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            List<String> ingredientList = getIngredientList(ingredientIds[i]);
            FoodItem foodItem = new FoodItem(titles[i], desc[i], foodChefs[i], headerImageIds[i], foodTypeIconIds[i], ingredientList);
            mlist.add(foodItem);
        }
        return mlist;
    }

    private List<String> getIngredientList(int ingredientId) {
        String[] ingredients = context.getResources().getStringArray(ingredientId);
        return Arrays.asList(ingredients);
    }
}
