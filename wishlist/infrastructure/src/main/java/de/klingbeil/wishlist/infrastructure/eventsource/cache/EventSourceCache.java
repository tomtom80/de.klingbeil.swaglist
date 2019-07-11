package de.klingbeil.wishlist.infrastructure.eventsource.cache;

import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventSourceCache {

  @Bean
  public Cache cache() {
    return new WeakReferenceCache();
  }
}
