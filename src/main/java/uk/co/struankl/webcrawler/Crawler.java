package uk.co.struankl.webcrawler;

import uk.co.struankl.webcrawler.model.CrawlMap;

/**
 * Created by struan on 24/09/16.
 */
public interface Crawler {
    public CrawlMap crawlDoc(String url);
}
