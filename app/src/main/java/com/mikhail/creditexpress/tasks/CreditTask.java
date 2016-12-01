package com.mikhail.creditexpress.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.parser.HtmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class CreditTask extends AsyncTask<String, Void, Void> {

    public List<CreditInfo> credit;

    @Override
    protected Void doInBackground(String... params) {
        credit = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            List<CreditInfo> info = null;
            try {
                info = HtmlParser.parseUrl(params[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            credit.addAll(info);
        }
        return null;
    }

}
