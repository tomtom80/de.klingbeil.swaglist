package de.klingbeil.swaglist.wishlist.persistence.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages ="de.klingbeil.swaglist.wishlist")
@Configuration
public class MongoConfig {

}
