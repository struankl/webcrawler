package uk.co.struankl.webcrawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.co.struankl.webcrawler.Crawler;
import uk.co.struankl.webcrawler.model.CrawlMap;
import uk.co.struankl.webcrawler.model.LinkType;
import uk.co.struankl.webcrawler.restmodel.RestCrawlMap;
import uk.co.struankl.webcrawler.restmodel.RestLink;
import uk.co.struankl.webcrawler.restmodel.RestPage;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by struan on 24/09/16.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan("uk.co.struankl.webcrawler")
public class WebCrawlerController {
    @Autowired
    Crawler crawler;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebCrawlerController.class, args);
    }

    @RequestMapping("/webcrawl")
    @ResponseBody
    public RestCrawlMap crawl(@RequestParam("url") String url) {
        CrawlMap crawlMap = crawler.crawlDoc(url);
        RestCrawlMap result = new RestCrawlMap();

        final List<RestPage> restPages = crawlMap.getMapUrls().stream().map(page -> {
            final Function<LinkType, RestLink> getRestLink = getLinkTypeRestLinkFunction(crawlMap, page);
            final RestPage restPage = new RestPage();
            restPage.setUrl(page);


            final List<RestLink> restLinks = crawlMap.getPageMap(page)
                    .getLinkTypes()
                    .stream()
                    .map(getRestLink)
                    .collect(toList());
            restPage.setLinksPerType(restLinks);
            return restPage;
        }).collect(toList());

        result.setUrl(url);
        result.setPages(restPages);
        return result;
    }

    private Function<LinkType, RestLink> getLinkTypeRestLinkFunction(CrawlMap crawlMap, String pageUrl) {
        return linkType -> {
            final RestLink restLink = new RestLink();
            restLink.setLinkType(linkType.name());
            restLink.setLinks(crawlMap.getPageMap(pageUrl).getLinks(linkType));
            return restLink;
        };
    }
}
