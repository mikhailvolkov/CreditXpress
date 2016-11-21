package com.mikhail.creditexpress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class ListUtil {
    public static List<CreditInfo> getDataInSingleList(List<List<CreditInfo>> data) {
        List<CreditInfo> result = new ArrayList<>();
        for (List<CreditInfo> list : data) {
            for (CreditInfo info : list) {
                result.add(info);
            }
        }
        return result;
    }
}
