package de.klingbeil.wishlist.wishes.query;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "wish", path = "wish")
public interface WishViewRepository extends JpaRepository<WishView, String> {
  List<WishView> findByWishlistId(String wishlistId);

  void deleteByIdentifierAndWishlistId(String identifier, String wishlistId);
}
