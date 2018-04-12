package com.simformsolutions.awesomeanimations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsVH> {

    LayoutInflater inflater;
    Context context;
    List<String> mlist;

    public IngredientsAdapter(Context context, List<String> mlist) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mlist = mlist;
    }

    @Override
    public IngredientsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row_ingredient_list, parent, false);
        return new IngredientsVH(view);
    }

    @Override
    public void onBindViewHolder(IngredientsVH holder, int position) {
        String item = mlist.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class IngredientsVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvIngredientName)
        TextView tvIngredientName;

        public IngredientsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String item) {
            tvIngredientName.setText(item);
        }
    }
}