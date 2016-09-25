package uk.co.struankl.webcrawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.struankl.webcrawler.model.CrawlMap;
import uk.co.struankl.webcrawler.model.LinkType;
import uk.co.struankl.webcrawler.model.PageMap;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toSet;

/**
 * Created by struan on 24/09/16.
 */
@Component
public class CrawlerImpl implements Crawler {

    final Function<String, String> removeQueryAndHash = link -> {
        int queryStart = link.indexOf("?");
        int hashStart = link.indexOf("#");
        if (hashStart < 0 && queryStart < 0) {
            return link;
        }
        if (hashStart >= 0 && queryStart >= 0) {
            return link.substring(Math.min(hashStart, queryStart));
        }
        if (hashStart >= 0) {
            return link.substring(hashStart);
        }
        return link.substring(queryStart);
    };
    @Autowired
    Parser parser;

    public CrawlMap crawlDoc(String url) {
        CrawlMap crawlMap = new CrawlMap();
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            System.err.format("could not crawl %s : %s", url, e);
            return null;
        }
        String domain = uri.getHost();
        Pattern domainPattern = Pattern.compile("^https?://[a-zA-Z0-9.]*" + domain + "(?:/.*)");
        crawlDoc(url, domainPattern, crawlMap);
        return crawlMap;
    }

    private void crawlDoc(String url, Pattern domain, CrawlMap crawlMap) {
        try {
            PageMap pageLinks = parsePage(url);
            crawlMap.addPageMap(pageLinks);
            pageLinks.getLinks(LinkType.LINK)
                    .stream()
                    .map(removeQueryAndHash)
                    .filter(link -> !crawlMap.pageHasBeenVisited(link))
                    .filter(link -> domain.matcher(link).matches())
                    .forEach(link -> {
                        crawlDoc(link, domain, crawlMap);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PageMap parsePage(String url) throws IOException {
        PageMap pageMap = new PageMap(url);

        Document doc = parser.parse(url);


        Arrays.asList(LinkType.values()).stream().forEach(lt -> {
            final Optional<Collection<String>> links = getLinks(doc, lt);

            if (links.isPresent()) {
                pageMap.addLinks(lt, links.get());
            }
        });
        return pageMap;
    }

    private Optional<Collection<String>> getLinks(Document doc, LinkType linkType) {
        final Function<Element, String> getLink = el -> el.attr(linkType.getAttribute());
        Set<String> stylesheets = doc.select(linkType.getCssSelector())
                .stream()
                .map(getLink)
                .collect(toSet());

        return stylesheets == null || stylesheets.size() == 0 ? Optional.empty() : Optional.of(stylesheets);
    }
}
