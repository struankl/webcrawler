package uk.co.struankl.webcrawler.restmodel;

import java.util.Collection;

/**
 * Created by struan on 25/09/16.
 */
public class RestLink {
    private String linkType;
    private Collection<String> links;

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public Collection<String> getLinks() {
        return links;
    }

    public void setLinks(Collection<String> links) {
        this.links = links;
    }
}
