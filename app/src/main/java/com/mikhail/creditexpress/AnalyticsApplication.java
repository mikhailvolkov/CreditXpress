package com.mikhail.creditexpress;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/* *
@author Volkov Mikhail
*/
public class AnalyticsApplication extends Application{
    private Tracker mTracker;

    /**
     * Получает счетчик {@link Tracker}, используемый по умолчанию для этого приложения {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // Чтобы включить ведение журнала отладки, используйте adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker("UA-61217032-1");
        }
        return mTracker;
    }

}
