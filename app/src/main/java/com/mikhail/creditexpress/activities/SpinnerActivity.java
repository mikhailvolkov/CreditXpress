package com.mikhail.creditexpress.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.R;
import com.mikhail.creditexpress.filter.Filter;

import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class SpinnerActivity extends Activity {
    private final String[] summData = {"10000 руб.", "15000 руб.", "20000 руб.", "25000 руб.", "30000 руб.", "40000 руб.", "50000 руб.", "60000 руб.", "70000 руб.", "80000 руб.", "90000 руб."};
    private final String[] timeData = {"15 дней", "25 дней", "30 дней", "35 дней", "45 дней", "60 дней", "3 мес.", "6 мес.", "12 мес.", "18 мес."};
    private final String[] methodData = {"Карта", "Счет", "Qiwi", "Contact", "Дом", "Офис"};
    private List<CreditInfo> creditDataCollection;
    private Spinner summ;
    private Spinner time;
    private Spinner method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);
        Intent intent = getIntent();
        creditDataCollection = (List<CreditInfo>) intent.getSerializableExtra("data");
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);
        initSpinners();
        configurateSpinner(summ, getAdapter(summData));
        configurateSpinner(time, getAdapter(timeData));
        configurateSpinner(method, getAdapter(methodData));

    }

    private void initSpinners() {
        summ = (Spinner) findViewById(R.id.summ_spinner);
        time = (Spinner) findViewById(R.id.time_spinner);
        method = (Spinner) findViewById(R.id.method_spinner);
    }

    private void configurateSpinner(Spinner spinner, ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
        // выделяем элемент
        spinner.setSelection(0);
    }

    public void filtrateCreditList(View view) {
        Filter filter = new Filter(creditDataCollection, this);
        List<CreditInfo> filtrated = filter.filtrate(
                getValue(summ.getSelectedItem().toString()),
                getValue(time.getSelectedItem().toString()),
                method.getSelectedItem().toString().toLowerCase());
        if (filtrated.size() == 0) {
            showToast();
        } else {
            Intent intent = new Intent(this, FilterResultActivity.class);
            intent.putExtra("filtratedList", (java.io.Serializable) filtrated);
            startActivity(intent);
        }
    }

    public void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Таких организаций нет!", Toast.LENGTH_SHORT);
        toast.show();

    }

    private int getValue(String input) {
        String[] mas = input.split(" ");
        if (mas[1].equals("мес.")) {
            return Integer.parseInt(mas[0]) * 31;
        }
        return Integer.parseInt(mas[0]);
    }

    private ArrayAdapter<String> getAdapter(String[] input) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, input);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
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
