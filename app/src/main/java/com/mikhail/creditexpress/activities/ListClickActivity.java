package com.mikhail.creditexpress.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.creditexpress.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * Created by Misha on 12.11.2014.
 */

public class ListClickActivity extends Activity {

    String position = "1";
    String iconPath = "";
    String timeValue = "";
    String summValue = "";
    String methodValue = "";
    String percentValue = "";
    String timeOfConsiderationValue = "";
    String url = "";

    ImageView icon;
    TextView time;
    TextView summ;
    TextView method;
    TextView percent;
    TextView timeOfConsideration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);
        try {

            //handle for the UI elements
            icon = (ImageView) findViewById(R.id.detail_page_image);
            //Text fields
            time = (TextView) findViewById(R.id.detail_page_time);
            summ = (TextView) findViewById(R.id.detail_page_summ);
            method = (TextView) findViewById(R.id.detail_page_method);
            percent = (TextView) findViewById(R.id.detail_page_percent);
            timeOfConsideration = (TextView) findViewById(R.id.detail_page_time_of_consideration);
            // Get position to display
            Intent i = getIntent();

            this.iconPath = i.getStringExtra("icon");
            this.timeValue = i.getStringExtra("time");
            this.summValue = i.getStringExtra("summ");
            this.methodValue = i.getStringExtra("method");
            this.percentValue = i.getStringExtra("percent");
            this.timeOfConsiderationValue = i.getStringExtra("timeOfConsideration");
            this.url = i.getStringExtra("link");
            Picasso.with(this)
                    .load(iconPath)
                    .into(icon);
            //text elements
            time.setText(timeValue);
            summ.setText(summValue);
            method.setText(methodValue);
            percent.setText(percentValue);
            timeOfConsideration.setText(timeOfConsiderationValue);
        } catch (Exception ex) {
        }

    }

    public void onRefButtonClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
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