package de.klingbeil.wishlist.users.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserViewRepository extends JpaRepository<UserView, String> {

  UserView findByUsername(String username);

  UserView findByIdentifier(String identifier);
}
