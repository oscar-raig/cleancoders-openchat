package org.openchat.infrastructure;

import org.openchat.domain.users.User;

public class UserBuilder {
  private String id;
  private String username;
  private String about;

  public static UserBuilder aUser() {
    return new UserBuilder();
  }
  public UserBuilder withId(String id) {
    this.id = id;
    return this;
  }

  public UserBuilder withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserBuilder withAbout(String about) {
    this.about = about;
    return this;
  }

  public User build() {
    return new User(id, username, about);
  }
}