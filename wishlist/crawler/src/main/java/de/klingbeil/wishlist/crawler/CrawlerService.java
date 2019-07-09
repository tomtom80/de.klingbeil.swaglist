package de.klingbeil.wishlist.crawler;

import java.io.IOException;
import java.net.URL;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klingbeil.wishlist.core.api.crawler.CrawleUrlCommand;
import de.klingbeil.wishlist.core.api.wishes.WishUpdateCommand;
import io.umehara.ogmapper.DefaultOgMapper;
import io.umehara.ogmapper.domain.OgTags;
import io.umehara.ogmapper.jsoup.JsoupOgMapperFactory;

@Service
public class CrawlerService {

	private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

	private final CommandGateway commandGateway;

	@Autowired
	public CrawlerService(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@CommandHandler
	public void handle(CrawleUrlCommand command) {

		String title = null;
		String url = command.getUrl();
		DefaultOgMapper ogMapper = new JsoupOgMapperFactory().build();

		try {
			OgTags ogTags = ogMapper.process(new URL(url));
			title = ogTags.getTitle();
		} catch (IOException e) {
			log.error("IOExecption", e);
		} catch (Exception e) {
			log.error("Exception", e);
		}

		if (title != null) {
			commandGateway.send(new WishUpdateCommand(command.getWishId(), command.getWishlistId(), title,
					"description", "location"));
		}
	}

}
