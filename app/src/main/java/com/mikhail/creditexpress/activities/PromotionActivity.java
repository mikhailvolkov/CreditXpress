package com.mikhail.creditexpress.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mikhail.creditexpress.R;
import com.mikhail.creditexpress.activities.adapters.PromotionRecyclerAdapter;

import java.util.ArrayList;

public class      PromotionActivity extends
        ActionBarActivity {
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private PromotionRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_recycler_view);

        ArrayList<String> myDataset = getDataSet();

        mRecyclerView = (RecyclerView) findViewById(R.id.promotion_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PromotionRecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> getDataSet() {

        ArrayList<String> mDataSet = new ArrayList();

        for (int i = 0; i < 100; i++) {
            mDataSet.add(i, "item" + i);
        }
        mDataSet.add(mDataSet.size(), "пункт" + mDataSet.size() + 1);
        return mDataSet;
    }

}
