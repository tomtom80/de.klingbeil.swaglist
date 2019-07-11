package de.klingbeil.wishlist.wishlists.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "wishlist", path = "wishlist")
public interface WishlistViewRepository extends JpaRepository<WishlistView, String> {

}
