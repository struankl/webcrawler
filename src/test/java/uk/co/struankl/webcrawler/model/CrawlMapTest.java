package uk.co.struankl.webcrawler.model;

import org.junit.Before;
import org.junit.Test;
import uk.co.struankl.webcrawler.model.CrawlMap;
import uk.co.struankl.webcrawler.model.PageMap;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by struan on 24/09/16.
 */
public class CrawlMapTest {
    CrawlMap testee = new CrawlMap();
    PageMap pageMap = new PageMap("test");
    @Before
    public void setUp() {
        testee.addPageMap(pageMap);
    }

    @Test
    public void getPageMap() throws Exception {
        assertSame(pageMap, testee.getPageMap("test"));
        assertNull(testee.getPageMap("notest"));
    }

    @Test
    public void getMapUrls() throws Exception {
        final Collection<String> mapUrls = testee.getMapUrls();
        assertEquals(1, mapUrls.size());
        assertEquals("test", mapUrls.iterator().next());
    }

    @Test
    public void pageHasBeenVisited() throws Exception {
        assertTrue(testee.pageHasBeenVisited("test"));
        assertFalse(testee.pageHasBeenVisited("notest"));
    }

}