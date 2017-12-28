package de.klingbeil.swaglist.wishlist.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import de.klingbeil.swaglist.wishlist.persistence.model.WishItemModel;

@Repository
public interface WishItemRepository extends MongoRepository<WishItemModel, String> {

}
