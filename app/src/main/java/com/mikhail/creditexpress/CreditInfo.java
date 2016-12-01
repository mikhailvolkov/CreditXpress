package com.mikhail.creditexpress;

import java.io.Serializable;

/**
 * @author Volkov Mikhail
 */
public class CreditInfo implements Serializable {
    private final String creditTime;//срок займа
    private final String creditSumm;// сумма займа
    private final String method;//способ получения
    private final String interestRate;//процентная ставка
    private final String timeOfConsideration;//время рассмотрения
    private final String partnerlink;//ссылка
    private final String SITE_URL = "http://credit-xpress.ru";
    private final String imageLink;

    public CreditInfo(String timeOfConsideration, String partnerlink,
                      String imageLink, String interestRate, String method,
                      String creditSumm, String creditTime) {
        this.timeOfConsideration = timeOfConsideration;
        this.partnerlink = partnerlink;
        this.imageLink = imageLink;
        this.interestRate = interestRate;
        this.method = method;
        this.creditSumm = creditSumm;
        this.creditTime = creditTime;
    }


    public String getCreditTime() {
        return creditTime;
    }

    public String getCreditSumm() {
        return creditSumm;
    }

    public String getMethod() {
        return method;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public String getTimeOfConsideration() {
        return timeOfConsideration;
    }

    public String getPartnerLink() {
        return SITE_URL + partnerlink;
    }

    public String getImageLink() {
        return SITE_URL + imageLink;
    }

}