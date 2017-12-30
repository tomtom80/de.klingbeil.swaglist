package de.klingbeil.swaglist.wishlist.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;
import de.klingbeil.swaglist.wishlist.api.model.UrlContent;
import de.klingbeil.swaglist.wishlist.api.model.UrlDto;

@RestController
public class TextCrawlerController {

  @PostMapping(value = "/content")
  public UrlContent parseUrl(@RequestBody UrlDto resource) {
    String url = resource.getUrl();
    SourceContent scrape = TextCrawler.scrape(url, 400);
    return scrape.isSuccess() ? toUrlContent(scrape, url) : toUrlContent(url);
  }

  private UrlContent toUrlContent(String term) {
    UrlContent urlContent = new UrlContent();
    urlContent.setTerm(term);
    return urlContent;
  }

  private UrlContent toUrlContent(SourceContent scrape, String url) {
    UrlContent content = toUrlContent(url);
    content.setDescription(scrape.getDescription());
    content.setFinalUrl(scrape.getFinalUrl());
    content.setRaw(scrape.getRaw());
    content.setTitle(scrape.getTitle());
    content.setUrl(scrape.getUrl());
    content.setSuccessful(scrape.isSuccess());
    return content;
  }

}
