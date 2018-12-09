package org.openchat.domain.users;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    User rhs = (User) obj;
    return new EqualsBuilder()
        .append(this.id, rhs.id)
        .append(this.username, rhs.username)
        .append(this.about, rhs.about)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(id)
        .append(username)
        .append(about)
        .toHashCode();
  }
}
