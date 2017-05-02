package com.mikhail.creditexpress.filter;

import android.content.Context;
import com.mikhail.creditexpress.CreditInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Производит фильтрацию списка кредитных организаций по параметрам заполняемым в
 * @see com.mikhail.creditexpress.activities.FilterActivity
 * @author Volkov Mikhail
 */
public class Filter {
    private List<CreditInfo> credits;

    public Filter(List<CreditInfo> credits) {
        this.credits = credits;
    }

    public List<CreditInfo> filtrate(int summ, int time, String method) {
        List<CreditInfo> filtratedCredits = new ArrayList<>();
        for (CreditInfo credit : credits) {
            if ((convertValue(credit.getCreditSumm()) >= summ )
                    && convertValue(credit.getCreditTime()) >= time
                    && credit.getMethod().contains(method)) {
                filtratedCredits.add(credit);
            }

        }
        return filtratedCredits;
    }

    public int convertValue(String input) {
        String[] mas = input.split(" ");
        if (input.equals("-")) {
            return 365;
        }
        switch (mas[2]) {
            case "нед.":
                return Integer.parseInt(mas[1]) * 7;
            case "мес.":
                return Integer.parseInt(mas[1]) * 31;
            case "лет":
                return Integer.parseInt(mas[1]) * 365;
            case "млн.":
                return (int) (Double.parseDouble(mas[1]) * 1000000);
        }
        return Integer.parseInt(mas[1]);
    }
}
