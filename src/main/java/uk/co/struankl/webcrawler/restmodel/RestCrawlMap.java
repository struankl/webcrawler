package uk.co.struankl.webcrawler.restmodel;

import java.util.Collection;

/**
 * Created by struan on 25/09/16.
 */
public class RestCrawlMap {
    private String url;
    private Collection<RestPage> pages;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<RestPage> getPages() {
        return pages;
    }

    public void setPages(Collection<RestPage> pages) {
        this.pages = pages;
    }
}
