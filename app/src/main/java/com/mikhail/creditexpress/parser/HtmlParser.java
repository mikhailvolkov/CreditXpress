package com.mikhail.creditexpress.parser;

import com.mikhail.creditexpress.CreditInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class HtmlParser {

    public static List<CreditInfo> parseUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).timeout(0).get();
        List<Element> partnerLinks = doc.select("div.warp-btn2").select("span.async-link");
        List<CreditInfo> creditList = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < partnerLinks.size(); i++) {
            String partnerLink = partnerLinks.get(i).attr("data-link");
            String imageLink = doc.select("div.catItemImageBlock").get(i).select("span img").attr("src");
            List<Element> info;
            if (i == 0) {
                counter = i + 1;
                info = doc.select("div#tbhead table").get(counter).select("span");
            } else {
                counter = counter + 2;
                info = doc.select("div#tbhead table").get(counter).select("span");
            }
            String creditTime = info.get(0).text().toLowerCase();//срок займа
            String methodText = info.get(1).text();
            String[] temp = methodText.split(" ");
            String creditSumm = temp[0].toLowerCase() + " " + temp[1].toLowerCase() + " " + temp[2].toLowerCase();// сумма займа
            if (methodText.contains("/")) {
                methodText = temp[3].toLowerCase() + temp[4].toLowerCase() + temp[5].toLowerCase();//способ получения
            } else {
                methodText = temp[3].toLowerCase();//способ получения
            }
            String interestRate = info.get(2).text().toLowerCase();//процентная ставка
            String timeOfConsideration = info.get(3).text().toLowerCase();//время рассмотрени
            creditList.add(new CreditInfo(timeOfConsideration, partnerLink, imageLink, interestRate, methodText, creditSumm, creditTime));

        }
        return creditList;
    }

}
