package org.openchat.domain.users;

import org.junit.Before;
import org.junit.Test;
import org.openchat.domain.users.exceptions.UserExistsException;
import org.openchat.infrastructure.UserBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

  private UserRepository userRepository;
  private User ALICE = UserBuilder.aUser()
                        .withId("AN_ID")
                        .withUsername("alice")
                        .withAbout("AN_ABOUT")
                        .build();
  private User CHARLIE = UserBuilder.aUser()
      .withId("AN_ID")
      .withUsername("charlie")
      .withAbout("AN_ABOUT")
      .build();

  @Before public void
  setUp(){
    userRepository = new UserRepository();
  }

  @Test
  public void inform_when_a_username_is_already_taken() {

    userRepository.add(ALICE);

    assertThat(userRepository.isUsernameTaken(ALICE.getUsername())).isTrue();
    assertThat(userRepository.isUsernameTaken(CHARLIE.getUsername())).isFalse();
  }
}