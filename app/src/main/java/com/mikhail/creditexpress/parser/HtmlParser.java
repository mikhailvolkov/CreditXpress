package com.mikhail.creditexpress.parser;

import com.mikhail.creditexpress.Parseable;

import java.io.IOException;
import java.util.List;

/**
 * Абстрактный класс парсера данных с сайта
 * наследники
 * @see CreditInfoParser
 * @see PromotionInfoParser
 * @author Volkov Mikhail
 */
public abstract class HtmlParser<T extends Parseable> {
    public abstract List<T> parseUrl(String url) throws IOException;
}
