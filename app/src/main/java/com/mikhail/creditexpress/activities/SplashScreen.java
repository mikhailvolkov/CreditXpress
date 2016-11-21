package com.mikhail.creditexpress.activities;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;


import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.R;
import com.mikhail.creditexpress.tasks.CreditTaskExecutor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Volkov Mikhail
 */
public class SplashScreen extends Activity {

    //how long until we go to the next activity
    protected int _splashTime = 1000;

    private Thread splashTread;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        final SplashScreen sPlashScreen = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CircularProgressView progressView = (CircularProgressView) findViewById(R.id.progress_view);
                progressView.startAnimation();
            }
        }, 1000);
        //wait 5 sec
        // thread for displaying the SplashScreen
        final Intent i = new Intent();;
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this){
                        wait(_splashTime);
                        List<String> links = Arrays.asList("http://credit-xpress.ru/online", "http://credit-xpress.ru/online?start=17",
                                "http://credit-xpress.ru/cash", "http://credit-xpress.ru/kredity");
                        List<List<CreditInfo>> data = new ArrayList<>();
                        for (String link : links) {
                            data.add(CreditTaskExecutor.execute(link));
                        }
                        i.putExtra("data", (Serializable) data);
                    }

                } catch(InterruptedException e) {}
                finally {
                    finish();

                    i.setClass(sPlashScreen, CreditActivity.class);
                    startActivity(i);

                }
            }
        };
        splashTread.start();

    }


    //Function that will handle the touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized(splashTread){
                splashTread.notifyAll();
            }
        }
        return true;
    }

}