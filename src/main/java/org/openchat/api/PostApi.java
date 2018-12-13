package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import java.time.format.DateTimeFormatter;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

import static org.eclipse.jetty.http.HttpStatus.CREATED_201;

public class PostApi {

  private final PostService postService;

  public PostApi(PostService postService) {

    this.postService = postService;
  }

  public String createPost(Request req, Response res) {
    res.type("application/json");
    res.status(CREATED_201);
    String userId = req.params("userId");
    Post post = postService.createPost(userId, getTextFrom(req));
    return PostJson.jsonPost(post);
  }

  private static class PostJson {

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
  private String getTextFrom(Request req) {
    JsonObject jsonObject = JsonObject.readFrom(req.body());
    return jsonObject.getString("text", "");
  }
}
