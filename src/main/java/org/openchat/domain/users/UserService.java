package org.openchat.domain.users;

public class UserService {
  public User createUser(RegsitrationData registrationData) {
    return new org.openchat.infrastructure.UserBuilder().setId("1")
        .setUsername(registrationData.getUsername())
        .setAbout(registrationData.getAbout())
        .createUser();
  }
}
