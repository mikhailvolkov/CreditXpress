package com.mikhail.creditexpress.filter;

import android.content.Context;
import android.widget.Toast;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.NodeKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class Filter {
    private List<CreditInfo> credits;
    private Context context;

    public Filter(List<CreditInfo> credits, Context context) {
        this.credits = credits;
        this.context = context;
    }

    public List<CreditInfo> filtrate(int summ, int time, String method) {
        List<CreditInfo> filtratedCredits = new ArrayList<>();
        for (CreditInfo credit : credits) {
            if ((getValue(credit.getCreditSumm()) >= summ )
                    && getValue(credit.getCreditTime()) >= time
                    && credit.getMethod().contains(method)) {
                filtratedCredits.add(credit);
            }

        }
        return filtratedCredits;
    }


    public int getValue(String input) {
        String[] mas = input.split(" ");
        if(input.equals("-")){
            return 365;
        }
        if (mas[2].equals("нед.")) {
            return Integer.parseInt(mas[1]) * 7;
        }
        if (mas[2].equals("мес.")) {
            return Integer.parseInt(mas[1]) * 31;
        }
        if (mas[2].equals("лет")) {
            return Integer.parseInt(mas[1]) * 365;
        }
        if (mas[2].equals("млн.")) {
            return Integer.parseInt(mas[1]) * 1000000;
        }

        return Integer.parseInt(mas[1]);
    }
}
