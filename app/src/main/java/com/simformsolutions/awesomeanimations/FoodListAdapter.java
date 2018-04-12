package com.simformsolutions.awesomeanimations;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListVH> {

    LayoutInflater inflater;
    Context context;
    List<FoodItem> mlist;
    OnItemClickListener listener;

    public FoodListAdapter(Context context, List<FoodItem> mlist) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mlist = mlist;
        listener = (OnItemClickListener) context;
    }

    @Override
    public FoodListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row_food_list, parent, false);
        return new FoodListVH(view);
    }

    @Override
    public void onBindViewHolder(FoodListVH holder, int position) {
        FoodItem item = mlist.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class FoodListVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.ivHeaderImage)
        ImageView ivHeaderImage;

        @BindView(R.id.ivFoodTypeIcon)
        ImageView ivFoodTypeIcon;

        @BindView(R.id.tvFoodName)
        TextView tvFoodName;

        @BindView(R.id.tvFoodChef)
        TextView tvFoodChef;

        private View rootView;

        private Context context;

        public FoodListVH(View itemView) {
            super(itemView);
            this.rootView = itemView;
            ButterKnife.bind(this, itemView);
            this.context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bind(FoodItem item) {
            Glide.with(context).load(item.getHearderImageId()).into(ivHeaderImage);
            Glide.with(context).load(item.getFoodTypeIconId()).into(ivFoodTypeIcon);
            tvFoodName.setText(item.getTitle());
            tvFoodChef.setText(item.getChefName());
            ViewCompat.setTransitionName(ivHeaderImage, item.getTransitionName());
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(mlist.get(getAdapterPosition()), getAdapterPosition(), v);
        }
    }

    interface OnItemClickListener {
        void onItemClick(FoodItem foodItem, int position, View itemView);
    }
}