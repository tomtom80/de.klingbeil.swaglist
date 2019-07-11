package de.klingbeil.wishlist.wishes.command;

import org.axonframework.common.caching.Cache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("command")
public class WishCommandConfiguration {
  @Bean
  public Repository<Wish> wishRepository(EventStore eventStore, Cache cache) {
    return EventSourcingRepository.builder(Wish.class).cache(cache).eventStore(eventStore).build();
  }
}
