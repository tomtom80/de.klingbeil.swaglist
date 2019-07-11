package de.klingbeil.wishlist.users.command;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import de.klingbeil.wishlist.core.api.users.AuthenticateUserCommand;
import de.klingbeil.wishlist.core.api.users.CreateUserCommand;
import de.klingbeil.wishlist.core.api.users.UserAuthenticatedEvent;
import de.klingbeil.wishlist.core.api.users.UserCreatedEvent;
import de.klingbeil.wishlist.core.api.users.UserId;
import de.klingbeil.wishlist.users.command.util.DigestUtils;

@Aggregate
public class User {

  @AggregateIdentifier
  private UserId userId;
  private String passwordHash;

  public User() {
    // Required by Axon Framework
  }

  @CommandHandler
  public User(CreateUserCommand cmd) {
    apply(new UserCreatedEvent(cmd.getUserId(), cmd.getName(), cmd.getUsername(),
        hashOf(cmd.getPassword().toCharArray())));
  }

  @CommandHandler
  public boolean handle(AuthenticateUserCommand cmd) {
    boolean success = this.passwordHash.equals(hashOf(cmd.getPassword()));
    if (success) {
      apply(new UserAuthenticatedEvent(userId));
    }
    return success;
  }

  @EventSourcingHandler
  public void on(UserCreatedEvent event) {
    this.userId = event.getUserId();
    this.passwordHash = event.getPassword();
  }

  private String hashOf(char[] password) {
    return DigestUtils.sha1(String.valueOf(password));
  }
}
