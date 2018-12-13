package org.openchat.domain.posts;

public class Post {
  private final String text;

  public Post(String text) {

    this.text = text;
  }

  public String getText() {
    return text;
  }
}
