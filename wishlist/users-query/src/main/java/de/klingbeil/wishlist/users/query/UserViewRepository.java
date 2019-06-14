package de.klingbeil.wishlist.users.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserViewRepository extends JpaRepository<UserView, String> {

	UserView findByUsername(String username);

	UserView findByIdentifier(String identifier);
}
