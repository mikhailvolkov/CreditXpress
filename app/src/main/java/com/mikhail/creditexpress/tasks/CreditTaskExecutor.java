package com.mikhail.creditexpress.tasks;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.activities.fragments.FullCatalog;

import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class CreditTaskExecutor {

    public static List<CreditInfo> execute(String... links){
        CreditTask task = (CreditTask) new CreditTask().execute(links);
        while (true) {
            if (task.credit != null
                    && task.credit.size() > 0
                    && task.isDataReceived) {
              return task.credit;
            }
        }
    }
}
