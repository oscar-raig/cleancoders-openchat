package org.openchat.domain.users;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.users.exceptions.UserExistsException;
import org.openchat.infrastructure.UserBuilder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  private static final String AN_ID = "1";
  private static final String A_USER_NAME = "aUserName";
  private static final String AN_ABOUT = "anAbout";
  private static final String A_PASSWORD = "DFDFD";


  @Mock private UserRepository userRepsitory;
  @Mock private IdGenerator idGenerator;

  private User USER = UserBuilder.aUser()
                          .withId(AN_ID)
                          .withUsername(A_USER_NAME)
                          .withAbout(AN_ABOUT)
                          .build();
  private UserService userservice;
  private RegsitrationData REGISTRATION_DATA = new RegsitrationData(A_USER_NAME, A_PASSWORD, AN_ABOUT);

  @Before public void setUp() {
    userservice = new UserService(userRepsitory, idGenerator);
  }

  @Test public void
  should_delegate_user_repository() {

    given(idGenerator.next()).willReturn(AN_ID);

    userservice.createUser(REGISTRATION_DATA);
    verify(userRepsitory).add(USER);
  }

  @Test ( expected = UserExistsException.class) public void
  should_throw_exception_when_attempt_to_create_duplicate_username() {


  }
}