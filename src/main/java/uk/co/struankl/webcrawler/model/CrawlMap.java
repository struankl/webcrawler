package uk.co.struankl.webcrawler.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by struan on 24/09/16.
 */
public class CrawlMap {
    Map<String, PageMap> map = new HashMap<>();

    public void addPageMap(PageMap pageMap) {
        map.put(pageMap.getUrl(), pageMap);
    }

    public PageMap getPageMap(String url) {
        return map.get(url);
    }

    public Collection<String> getMapUrls() {
        return map.keySet();
    }

    public boolean pageHasBeenVisited(String url) {
        return map.containsKey(url);
    }
}
