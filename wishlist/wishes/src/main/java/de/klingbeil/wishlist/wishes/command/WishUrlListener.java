package de.klingbeil.wishlist.wishes.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.klingbeil.wishlist.core.api.crawler.CrawleUrlCommand;
import de.klingbeil.wishlist.core.api.crawler.CrawlerId;
import de.klingbeil.wishlist.core.api.wishes.WishUrlAddedEvent;

@Service
@ProcessingGroup("commandPublishingEventHandlers")
public class WishUrlListener {
  private static final Logger logger = LoggerFactory.getLogger(WishUrlListener.class);

  private final CommandGateway commandGateway;

  @Autowired
  public WishUrlListener(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @EventHandler
  public void on(WishUrlAddedEvent event) {
    logger.debug("About to dispatch a new command to crawle a wish for the url {}",
        event.getWishUrl());
    commandGateway.send(new CrawleUrlCommand(new CrawlerId(), event.getWishlistId(),
        event.getWishId(), event.getWishUrl()));
  }
}
