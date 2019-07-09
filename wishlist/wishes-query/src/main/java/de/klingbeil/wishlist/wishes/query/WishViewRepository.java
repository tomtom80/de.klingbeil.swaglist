package de.klingbeil.wishlist.wishes.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishViewRepository extends JpaRepository<WishView, String> {
	List<WishView> findByWishlistId(String wishlistId);
}
