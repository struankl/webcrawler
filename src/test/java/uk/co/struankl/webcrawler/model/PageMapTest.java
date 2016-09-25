package uk.co.struankl.webcrawler.model;

import org.junit.Before;
import org.junit.Test;
import uk.co.struankl.webcrawler.model.PageMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static uk.co.struankl.webcrawler.model.LinkType.IMAGE;
import static uk.co.struankl.webcrawler.model.LinkType.LINK;

/**
 * Created by struan on 24/09/16.
 */
public class PageMapTest {
    PageMap testee = new PageMap("test");
    final List<String> links = Arrays.asList(new String[]{"testLink"});

    @Before
    public void setUp() throws Exception {
        testee.addLinks(LINK, links);
    }

    @Test
    public void getUrl() throws Exception {
        assertEquals("test", testee.getUrl());
    }

    @Test
    public void getLinks() throws Exception {
        assertSame(links, testee.getLinks(LINK));
        assertEquals(0, testee.getLinks(IMAGE).size());
    }

    @Test
    public void getLinkTypes() throws Exception {
        assertEquals(1, testee.getLinkTypes().size());
        assertEquals(LINK, testee.getLinkTypes().iterator().next());
    }

}