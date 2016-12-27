package com.mikhail.creditexpress.tasks;

import com.mikhail.creditexpress.Parseable;
import com.mikhail.creditexpress.parser.HtmlParser;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Активирует
 * @see ParseInfoTask
 * @author Volkov Mikhail
 */
public class TaskExecutor {

    public List<? extends Parseable> execute(HtmlParser<? extends Parseable> parser, String... links) {
        try {
            return new ParseInfoTask(parser).execute(links).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
