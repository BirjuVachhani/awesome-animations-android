package com.simformsolutions.awesomeanimations;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.collapsingToolBar)
    CollapsingToolbarLayout mCollapsingToolBar;

    @BindView(R.id.ivHeaderImage)
    ImageView ivHeaderImage;

    @BindView(R.id.ivFoodTypeIcon)
    ImageView ivFoodTypeIcon;

    @BindView(R.id.tvFoodName)
    TextView tvFoodName;

    @BindView(R.id.tvFoodChef)
    TextView tvFoodChef;

    @BindView(R.id.tvFoodDesc)
    TextView tvFoodDesc;

    @BindView(R.id.rvIngredientList)
    RecyclerView rvIngredientList;

    @BindView(R.id.fabFavorite)
    FloatingActionButton fabFavorite;

    @BindView(R.id.rootView)
    CoordinatorLayout rootView;

    private boolean isFavorited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);
        setToolBar();
        if (getIntent() != null) {
            FoodItem foodItem = getIntent().getParcelableExtra(MainActivity.ITEM_LABLE);
            supportPostponeEnterTransition();
            setReceivedData(foodItem);
        }
    }

    private void setReceivedData(FoodItem foodItem) {
        getSupportActionBar().setTitle(foodItem.getTitle());
        ivHeaderImage.setTransitionName(foodItem.getTransitionName());
        Picasso.get().load(foodItem.getHearderImageId()).placeholder(R.drawable.placeholder2).noFade().into(ivHeaderImage, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError(Exception e) {
            }
        });
        Picasso.get().load(foodItem.getFoodTypeIconId()).fit().noFade().into(ivFoodTypeIcon);
        tvFoodName.setText(foodItem.getTitle());
        tvFoodChef.setText(foodItem.getChefName());
        tvFoodDesc.setText(foodItem.getDescription());
        IngredientsAdapter adapter = new IngredientsAdapter(this, foodItem.getIngredientList());
        rvIngredientList.setLayoutManager(new LinearLayoutManager(this));
        rvIngredientList.setAdapter(adapter);
    }

    private void setToolBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolBar.setTitle(null);
        mCollapsingToolBar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pesto_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void toggleFavorite(View view) {
        if (isFavorited) {
            isFavorited = false;
            fabFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            Snackbar.make(view, R.string.fav_removed, Snackbar.LENGTH_LONG).show();
            return;
        }
        isFavorited = true;
        fabFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        Snackbar.make(view, R.string.fav_added, Snackbar.LENGTH_LONG).show();
    }
}
