package org.openchat.domain.posts;

import java.util.Date;

public class Post {

  private final String postId;
  private final String userId;
  private final String text;
  private final Date date;

  public Post(String postId, String userId, String text, Date date) {

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

  public Date getDate() {
    return date;
  }
}
