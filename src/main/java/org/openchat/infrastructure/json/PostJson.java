package org.openchat.infrastructure.json;

import com.eclipsesource.json.JsonObject;
import java.time.format.DateTimeFormatter;
import org.openchat.domain.posts.Post;

public class PostJson {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

  public static String jsonPost(Post post) {
    return new JsonObject()
        .add("postId", post.getPostId())
        .add("userId", post.getUserId())
        .add("text", post.getText())
        .add("dateTime", dateTimeFormatter.format(post.getDate()))
        .toString();
  }
}
