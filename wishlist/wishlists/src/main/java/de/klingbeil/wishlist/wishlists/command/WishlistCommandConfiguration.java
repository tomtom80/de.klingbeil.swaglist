package de.klingbeil.wishlist.wishlists.command;

import org.springframework.context.annotation.Bean;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("command")
public class WishlistCommandConfiguration {
	@Bean
    public Repository<Wishlist> wishlistRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(Wishlist.class)
                                      .cache(cache)
                                      .eventStore(eventStore)
                                      .build();
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
}
