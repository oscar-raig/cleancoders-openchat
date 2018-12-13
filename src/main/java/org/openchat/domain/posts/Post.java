package org.openchat.domain.posts;

import java.time.LocalDateTime;
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

  @Override public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Post post = (Post) o;

    return new EqualsBuilder()
        .append(postId, post.postId)
        .append(userId, post.userId)
        .append(text, post.text)
        .append(date, post.date)
        .isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(postId)
        .append(userId)
        .append(text)
        .append(date)
        .toHashCode();
  }
}
