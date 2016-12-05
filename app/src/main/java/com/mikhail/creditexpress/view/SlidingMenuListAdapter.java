package com.mikhail.creditexpress.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mikhail.creditexpress.R;

/**
 * @author Volkov Mikhail
 */
public class SlidingMenuListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] listRowsText;

    public SlidingMenuListAdapter(Activity context, String[] listRowsText) {
        super(context, R.layout.drawer_list_item, listRowsText);
        this.context = context;
        this.listRowsText = listRowsText;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.drawer_list_item, null, true);

        TextView textView = (TextView) rowView.findViewById(R.id.sliding_menu_listrow);
        textView.setText(listRowsText[position]);
        switch (position) {
            case 0:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        context.getResources().getDrawable(R.drawable.catalog), null, null, null);
                break;
            case 1:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        context.getResources().getDrawable(R.drawable.search), null, null, null);
                break;
            case 2:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        context.getResources().getDrawable(R.drawable.promotion), null, null, null);
                break;
            case 3:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        context.getResources().getDrawable(R.drawable.help), null, null, null);
                break;

        }
        return rowView;
    }
}
