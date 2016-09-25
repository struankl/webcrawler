package uk.co.struankl.webcrawler.restmodel;

import java.util.Collection;

/**
 * Created by struan on 25/09/16.
 */
public class RestPage {
    private String url;
    private Collection<RestLink> linksPerType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<RestLink> getLinksPerType() {
        return linksPerType;
    }

    public void setLinksPerType(Collection<RestLink> linksPerType) {
        this.linksPerType = linksPerType;
    }
}
