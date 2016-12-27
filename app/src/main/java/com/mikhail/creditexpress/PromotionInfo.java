package com.mikhail.creditexpress;

/**
 * Используется для хранения информации карточки раздела "Акции"
 * @author Volkov Mikhail
 */
public class PromotionInfo extends Info implements Parseable {
    private final String sloganHeader;
    private final String sloganBody;
    private final String organizationImage;//пикча кредитной организации
    private final String promotionImage;//основная пикча акции
    private final String additionalInfoLink;

    public PromotionInfo(String partnerlink, String sloganHeader, String sloganBody,
                         String organizationImage, String promotionImage, String additionalInfoLink) {
        super(partnerlink);
        this.sloganHeader = sloganHeader;
        this.sloganBody = sloganBody;
        this.organizationImage = organizationImage;
        this.promotionImage = promotionImage;
        this.additionalInfoLink = additionalInfoLink;
    }

    public String getSloganHeader() {
        return sloganHeader;
    }

    public String getSloganBody() {
        return sloganBody;
    }

    public String getOrganizationImageLink() {
        return SITE_URL + organizationImage;
    }

    public String getPromotionImageLink() {
        return SITE_URL + promotionImage;
    }

    public String getAdditionalInfoLink() {
        return SITE_URL + additionalInfoLink;
    }

}
