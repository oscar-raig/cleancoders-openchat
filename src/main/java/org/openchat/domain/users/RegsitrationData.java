package org.openchat.domain.users;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RegsitrationData {

  private final String username;
  private final String password;
  private final String about;

  public RegsitrationData(String username, String password, String about) {

    this.username = username;
    this.password = password;
    this.about = about;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getAbout() {
    return about;
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
    RegsitrationData rhs = (RegsitrationData) obj;
    return new EqualsBuilder()
        .append(this.username, rhs.username)
        .append(this.password, rhs.password)
        .append(this.about, rhs.about)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(username)
        .append(password)
        .append(about)
        .toHashCode();
  }
}
