package com.simformsolutions.awesomeanimations;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FoodListAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

    @BindView(R.id.rvFoodList)
    RecyclerView rvFoodList;

    public static final String ITEM_LABLE = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbarAndDrawer();
        setFoodList();
    }

    private void setFoodList() {
        List<FoodItem> mlist = new ListGenerator(this).generate();
        FoodListAdapter adapter = new FoodListAdapter(this, mlist);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        rvFoodList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pesto_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setToolbarAndDrawer() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        ImageView ivAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
        Glide.with(this).load(R.drawable.ic_avatar).into(ivAvatar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(FoodItem foodItem) {
        Intent intent = new Intent(this, FoodDetailsActivity.class);
        intent.putExtra(ITEM_LABLE, foodItem);
        startActivity(intent);
    }

    public void showMessage(View view) {
        Snackbar.make(view, R.string.snackbar_msg, Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }
}
