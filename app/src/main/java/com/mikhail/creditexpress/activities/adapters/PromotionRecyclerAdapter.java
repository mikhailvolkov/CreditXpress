package com.mikhail.creditexpress.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhail.creditexpress.R;

import java.util.ArrayList;
/**
 * @author Volkov Mikhail
 */
public class PromotionRecyclerAdapter extends RecyclerView.Adapter<PromotionRecyclerAdapter.ViewHolder>{
    private ArrayList<String> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
//            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
        }
    }

    public PromotionRecyclerAdapter(ArrayList<String> dataset) {
        mDataset = dataset;
    }

    @Override
    public PromotionRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_recycler_item, parent, false);
//
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.mTextView.setText(mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
