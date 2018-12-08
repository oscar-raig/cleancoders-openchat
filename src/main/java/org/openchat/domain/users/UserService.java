package org.openchat.domain.users;

public class UserService {
  public User createUser(RegsitrationData registrationData) {
    return new User("1",
        registrationData.getUsername(),
        registrationData.getAbout());
  }
}
