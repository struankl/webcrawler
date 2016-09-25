package uk.co.struankl.webcrawler;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by struan on 25/09/16.
 */
public interface Parser {

    Document parse(String url) throws IOException;

}
