package uk.co.struankl.webcrawler.model;

/**
 * Created by struan on 24/09/16.
 */
public enum LinkType {
    STYLESHEET("head link[rel=\"stylesheet\"]", "abs:href"),
    IMAGE("body img", "abs:src"),
    SCRIPT("script[src]", "abs:src"),
    LINK("a", "abs:href");

    private String cssSelector;
    private String attribute;

    private LinkType(String cssSelector, String attribute) {
        this.cssSelector = cssSelector;
        this.attribute = attribute;
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public String getAttribute() {
        return attribute;
    }
}
