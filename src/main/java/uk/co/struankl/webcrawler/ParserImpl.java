package uk.co.struankl.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by struan on 25/09/16.
 * Bean-ize the Jsoup static method to allow testing via supplying different implementation via IoC
 */
@Component
public class ParserImpl implements Parser {
    @Override
    public Document parse(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
