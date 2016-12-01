package com.mikhail.creditexpress;

import android.content.Context;
import android.content.Intent;

import com.mikhail.creditexpress.activities.ListClickActivity;

import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class ListDataSender {
    private List<CreditInfo> creditDataCollection;
    private Context currentContext;

    public ListDataSender(List<CreditInfo> creditDataCollection, Context currentContext) {
        this.creditDataCollection = creditDataCollection;
        this.currentContext = currentContext;
    }

    public Intent getIntent(int position) {
        Intent i = new Intent();
        i.setClass(currentContext, ListClickActivity.class);
        // parameters
        i.putExtra("position", String.valueOf(position + 1));
        i.putExtra("credit_info", creditDataCollection.get(position));
        return i;
    }
}
