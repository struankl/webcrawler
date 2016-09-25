#Struan's Webcrawler Demo

##Prerequisites
* Java 8
* Maven

##Installation And Running
After cloning or downloading execute the following commands in the base directory
* `mvn install`
* `java -jar target/webcrawler-1.0-SNAPSHOT.jar`
* load http://localhost:8080 in a modern browser (because arrow functions are used - very simple to make IE compliant)

##Issues and Limitations
* Test coverage is _minimal_
* No feedback while crawling, and can take some time - wiprodigital.com takes about 35 seconds on my machine.
* Parsing errors (including dead links) cause a stacktrace in logs, but processing continues.
* CSS is not parsed, so no links included in styleshets are included.
* Some links are missed, eg background images
* Crawled pages presented in arbitrary order, request page should be first
* Will decend into subdomains, but not assend to super-domains, so if wiprodigital.com is requested links to www.wiprodigital.com will be followed, but if www.wiprodigital.com is requested links to wiprodigital.com will not be crawled.
* There is no css, ui is simple knockout page.
* Does not respect Robots.txt
* Only used master branch in github - not development branch/merging etc
