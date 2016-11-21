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
public class CreditInfoDownloadTask extends AsyncTask<String, Void, Void> {

    public List<CreditInfo> credit;
    public boolean isDataReceived = false;

    @Override
    protected Void doInBackground(String... params) {
        credit = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            while (true) {
                List<CreditInfo> info = null;
                try {
                    info = HtmlParser.parseUrl(params[i]);
                    if (info != null && info.size() > 0) {
                        credit.addAll(info);
                        if (i == params.length - 1) {
                            isDataReceived = true;
                            break;
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }


}
