package org.openchat.domain.posts;

import java.time.LocalDateTime;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Post {

  private final String postId;
  private final String userId;
  private final String text;
  private final LocalDateTime date;

  public Post(String postId, String userId, String text, LocalDateTime date) {

    this.postId = postId;
    this.userId = userId;
    this.text = text;
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public String getPostId() {
    return postId;
  }

  public String getUserId() {
    return userId;
  }

  public LocalDateTime getDate() {
    return date;
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
    Post rhs = (Post) obj;
    return new EqualsBuilder()
        .append(this.postId, rhs.postId)
        .append(this.userId, rhs.userId)
        .append(this.text, rhs.text)
        .append(this.date, rhs.date)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(postId)
        .append(userId)
        .append(text)
        .append(date)
        .toHashCode();
  }
}
