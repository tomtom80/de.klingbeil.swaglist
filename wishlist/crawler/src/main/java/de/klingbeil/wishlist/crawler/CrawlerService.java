package de.klingbeil.wishlist.crawler;

import java.net.URL;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.klingbeil.wishlist.core.api.crawler.CrawleUrlCommand;
import de.klingbeil.wishlist.core.api.wishes.WishUpdateCommand;
import io.umehara.ogmapper.DefaultOgMapper;
import io.umehara.ogmapper.domain.OgTags;
import io.umehara.ogmapper.jsoup.JsoupOgMapperFactory;

@Service
public class CrawlerService {

  private final CommandGateway commandGateway;

  @Autowired
  public CrawlerService(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @CommandHandler
  public void handle(CrawleUrlCommand command) {
    String urlAsString = command.getUrl();
    DefaultOgMapper ogMapper = new JsoupOgMapperFactory().build();
    try {
      URL url = new URL(urlAsString);
      OgTags ogTags = ogMapper.process(url);
      String location = ogTags.getSiteName();
      URL image = ogTags.getImage();
      commandGateway.send(new WishUpdateCommand(command.getWishId(), command.getWishlistId(),
          ogTags.getTitle(), ogTags.getDescription(), location, url, image));
    } catch (Exception e) {

    }
  }

}
