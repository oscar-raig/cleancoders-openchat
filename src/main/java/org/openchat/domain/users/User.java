package org.openchat.domain.users;

public class User {

  private final String id;
  private final String username;
  private final String about;

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getAbout() {
    return about;
  }

  public User(String id, String username, String about) {

    this.id = id;
    this.username = username;
    this.about = about;
  }
}
