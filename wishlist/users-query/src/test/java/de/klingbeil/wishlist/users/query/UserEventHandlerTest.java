package de.klingbeil.wishlist.users.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

import de.klingbeil.wishlist.core.api.users.UserCreatedEvent;
import de.klingbeil.wishlist.core.api.users.UserId;

@RunWith(MockitoJUnitRunner.class)
public class UserEventHandlerTest {

	private final UserViewRepository userViewRepository = mock(UserViewRepository.class);

	private UserEventHandler testSubject;

	@Before
	public void setUp() {
		testSubject = new UserEventHandler(userViewRepository);
	}

	@Test
	public void testOnUserCreatedEventAnUserViewIsSaved() {
		UserId expectedUserId = new UserId();
		String expectedName = "name";
		String expectedUserName = "userName";
		UserCreatedEvent testEvent = new UserCreatedEvent(expectedUserId, expectedName, expectedUserName, "password");

		testSubject.on(testEvent);

		ArgumentCaptor<UserView> userViewCaptor = ArgumentCaptor.forClass(UserView.class);
		verify(userViewRepository).save(userViewCaptor.capture());
		UserView result = userViewCaptor.getValue();
		assertNotNull(result);
		assertEquals(expectedUserId.getIdentifier(), result.getUserId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedUserName, result.getUserName());
	}
}