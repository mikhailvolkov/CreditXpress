package com.mikhail.creditexpress.parser;

import com.mikhail.creditexpress.PromotionInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Парсит информацию об акциях
 * @author Volkov Mikhail
 */
public class PromotionInfoParser extends HtmlParser<PromotionInfo> {
    @Override
    public List<PromotionInfo> parseUrl(String url) throws IOException {
        Document doc = getDoc(url);
        List<Element> promotions = doc.select("div.block-news");
        List<PromotionInfo> result = new ArrayList<>();
        for (int pos = 0; pos < promotions.size(); pos++) {
            Element promotionElement = promotions.get(pos);
            String promotionImage = promotionElement.select("img").get(0).attr("src");
            String organizationImage = doc.select("div.block-news-logo").get(pos).select("img").attr("src");
            Elements slogan = doc.select("div.block-news-slogan").get(pos).select("span");
            String sloganHeader = slogan.get(0).text();
            String sloganBody = slogan.get(1).text();
            String additionalInfo = promotionElement.select("a.block-news-link").attr("href");
            result.add(new PromotionInfo(null, sloganHeader, sloganBody, organizationImage, promotionImage, additionalInfo));
        }
        return result;
    }

//    private String getPartnerLink(int pos, Document doc) throws IOException {
//        ExecutorService service = Executors.newCachedThreadPool();
//        final String[] link = new String[1];
//        service.submit(new Runnable() {
//            public void run() {
//                while (true) {
//                    String articleLink = doc.select("a.block-news-link").get(pos).attr("href");
//                    try {
//                        String orgLink = getDoc(PartnerInfo.SITE_URL + articleLink).select("div#system").select("a").attr("href");
//                        link[0] = getDoc(PartnerInfo.SITE_URL + orgLink).select("div.warp-btn2").select("span").attr("data-link");
//                        if (link[0] != null) {
//                            break;
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        return link[0];
//    }

    private Document getDoc(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

}
