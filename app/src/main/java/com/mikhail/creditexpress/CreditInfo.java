package com.mikhail.creditexpress;


import java.io.Serializable;

/**
 * Используется для хранения информации о кредитной организации,
 * список данных организаций заполняется с помощью
 * @see com.mikhail.creditexpress.parser.CreditInfoParser
 * @author Volkov Mikhail
 */
public class CreditInfo  extends Info implements Serializable, Parseable {
    private final String creditTime;//срок займа
    private final String creditSumm;// сумма займа
    private final String method;//способ получения
    private final String interestRate;//процентная ставка
    private final String timeOfConsideration;//время рассмотрения
    private final String imageLink;

    public CreditInfo(String partnerlink, String creditTime, String creditSumm, String method, String interestRate, String timeOfConsideration, String imageLink) {
        super(partnerlink);
        this.creditTime = creditTime;
        this.creditSumm = creditSumm;
        this.method = method;
        this.interestRate = interestRate;
        this.timeOfConsideration = timeOfConsideration;
        this.imageLink = imageLink;
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

    public String getImageLink() {
        return SITE_URL + imageLink;
    }

}