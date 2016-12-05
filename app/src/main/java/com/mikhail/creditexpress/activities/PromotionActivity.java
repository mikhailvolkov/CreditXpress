package com.mikhail.creditexpress.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.mikhail.creditexpress.PromotionInfo;
import com.mikhail.creditexpress.R;
import com.mikhail.creditexpress.activities.adapters.PromotionRecyclerAdapter;

import java.util.List;

/**
 * @author Volkov Mikhail
 */

public class PromotionActivity extends
        Activity {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private PromotionRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_recycler_view);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText(
                Html.fromHtml("<font color=\"white\">" + "Акции" + "</font>"));
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);

        recycler = (RecyclerView) findViewById(R.id.promotion_recycler_view);
        recycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        adapter = new PromotionRecyclerAdapter((List<PromotionInfo>) getIntent().getSerializableExtra("promotion_data"), this);
        recycler.setAdapter(adapter);
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;

    }

}
