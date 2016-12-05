package com.mikhail.creditexpress.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.R;
import com.squareup.picasso.Picasso;


/**
 * @author Volkov Mikhail
 */

public class ListClickActivity extends Activity {
    private String partnerLink;
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

        initListRow();
    }

    private void initListRow() {
        ImageView icon = (ImageView) findViewById(R.id.detail_page_image);
        TextView time = (TextView) findViewById(R.id.detail_page_time);
        TextView summ = (TextView) findViewById(R.id.detail_page_summ);
        TextView method = (TextView) findViewById(R.id.detail_page_method);
        TextView percent = (TextView) findViewById(R.id.detail_page_percent);
        TextView timeOfConsideration = (TextView) findViewById(R.id.detail_page_time_of_consideration);
        Intent i = getIntent();

        CreditInfo creditInfo = (CreditInfo) i.getSerializableExtra("credit_info");
        partnerLink = creditInfo.getPartnerlink();
        Picasso.with(this)
                .load(creditInfo.getImageLink())
                .into(icon);
        //text elements
        time.setText(creditInfo.getCreditTime());
        summ.setText(creditInfo.getCreditSumm());
        method.setText(creditInfo.getMethod());
        percent.setText(creditInfo.getInterestRate());
        timeOfConsideration.setText(creditInfo.getTimeOfConsideration());
    }

    public void onRefButtonClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(partnerLink));
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