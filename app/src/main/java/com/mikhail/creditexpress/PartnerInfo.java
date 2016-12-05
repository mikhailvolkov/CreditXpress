package com.mikhail.creditexpress;

import java.io.Serializable;

/**
 * @author Volkov Mikhail
 */
public class PartnerInfo implements Serializable {
    private final String partnerlink;//ссылка
    public final static String SITE_URL = "http://credit-xpress.ru";

    public PartnerInfo(String partnerlink) {
        this.partnerlink = partnerlink;
    }

    public String getPartnerlink() {
        return SITE_URL + partnerlink;
    }
}
