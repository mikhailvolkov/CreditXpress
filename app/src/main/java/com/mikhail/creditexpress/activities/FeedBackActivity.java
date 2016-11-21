package com.mikhail.creditexpress.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mikhail.creditexpress.R;


/**
 * Created by Misha on 09.02.2015.
 */
public class FeedBackActivity extends Activity {
    private String email;
    private String text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_form);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);
    }
    private void send() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, email);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setData(Uri.parse("mailto:info@credit-xpress.ru"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(Intent.createChooser( intent , "Отправка сообщения..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(FeedBackActivity.this, "Установите почтовый клиент для отправки сообщения", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        EditText curEmail = (EditText) findViewById(R.id.email);
        EditText curText = (EditText) findViewById(R.id.text);
        email = curEmail.getText().toString();
        text = curText.getText().toString();
    }
    public void sendDataToTechSupport(View view){
        getData();
        send();
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
