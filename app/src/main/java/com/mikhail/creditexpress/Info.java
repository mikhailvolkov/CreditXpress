package com.mikhail.creditexpress;

import java.io.Serializable;

/**
 * Родительский класс для
 * @see  com.mikhail.creditexpress.CreditInfo
 * @see  com.mikhail.creditexpress.PromotionInfo
 * @author Volkov Mikhail
 */
public class Info implements Serializable {
    private final String partnerlink;//ссылка
    public final static String SITE_URL = "http://credit-xpress.ru";

    public Info(String partnerlink) {
        this.partnerlink = partnerlink;
    }

    public String getPartnerlink() {
        return SITE_URL + partnerlink;
    }
}
