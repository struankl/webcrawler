package uk.co.struankl.webcrawler.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

/**
 * Created by struan on 24/09/16.
 */
public class PageMap {

    private String url;
    private Map<String, Collection<String>> linkMap = new HashMap<>();

    public PageMap(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void addLinks(LinkType linkType, Collection<String> links) {
        linkMap.put(linkType.name(), links);
    }

    public Collection<String> getLinks(LinkType linkType) {
        return linkMap.containsKey(linkType.name()) ? linkMap.get(linkType.name()) : Collections.EMPTY_SET;
    }

    public Collection<LinkType> getLinkTypes() {
        return linkMap.keySet().stream().map(LinkType::valueOf).collect(toSet());
    }
}
