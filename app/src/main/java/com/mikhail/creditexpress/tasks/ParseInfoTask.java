package com.mikhail.creditexpress.tasks;

import android.os.AsyncTask;

import com.mikhail.creditexpress.activities.Parseable;
import com.mikhail.creditexpress.parser.HtmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class ParseInfoTask extends AsyncTask<String, Void, List<? extends Parseable>> {

    private HtmlParser<? extends Parseable> parser;

    public ParseInfoTask(HtmlParser<? extends Parseable> parser) {
        this.parser = parser;
    }

    @Override
    protected List<? extends Parseable> doInBackground(String... urls) {
        List<Parseable> result = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            while (true) {
                List<? extends Parseable> info = null;
                try {
                    info = parser.parseUrl(urls[i]);
                    if (info != null && info.size() > 0) {
                        result.addAll(info);
                        if (i == urls.length - 1) {
                            break;
                        }
                        break;
                    }
                } catch (IOException ignored) {
                }
            }
        }
        return result;
    }
}
