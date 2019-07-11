package de.klingbeil.wishlist.users.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.klingbeil.wishlist.core.api.users.UserCreatedEvent;

@Service
@ProcessingGroup("userQueryModel")
public class UserEventHandler {

  private final UserViewRepository userRepository;

  @Autowired
  public UserEventHandler(UserViewRepository userRepository) {
    this.userRepository = userRepository;
  }

  @EventHandler
  public void on(UserCreatedEvent event) {
    UserView userView = new UserView();

    userView.setIdentifier(event.getUserId().getIdentifier());
    userView.setName(event.getName());
    userView.setUsername(event.getUsername());
    userView.setPassword(event.getPassword());

    userRepository.save(userView);
  }
}
