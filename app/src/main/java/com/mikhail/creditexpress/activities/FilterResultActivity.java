package com.mikhail.creditexpress.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.DataBinder;
import com.mikhail.creditexpress.ListDataSender;
import com.mikhail.creditexpress.R;

import java.util.HashMap;
import java.util.List;


public class FilterResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.filterresult);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);
        Intent i = getIntent();
        List<CreditInfo> filtrated = (List<CreditInfo>) i.getSerializableExtra("filtratedList");

        ListView listView = (ListView) findViewById(R.id.filterResultList);
        DataBinder bindingData = new DataBinder(this, filtrated);
        bindingData.notifyDataSetChanged();
        listView.setAdapter(bindingData);
        onItemClicked(filtrated, listView);
        super.onCreate(savedInstanceState);
    }
    private void onItemClicked(List<CreditInfo> filtrated, ListView list) {
        final ListDataSender sender = new ListDataSender(filtrated, FilterResultActivity.this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startActivity(sender.getIntent(position));
            }
        });

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
