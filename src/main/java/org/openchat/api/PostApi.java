package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.openchat.domain.posts.NewPost;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

public class PostApi {

  private final PostService postService;

  public PostApi(PostService postService) {

    this.postService = postService;
  }

  public String createPost(Request req, Response res) {
    res.type("application/json");
    res.status(201);
    String userId = req.params("userId");
    Post post = postService.createPost(userId, createNewPostFrom(req));
    return jsonPost(post);
  }

  private String jsonPost(Post post) {
    return new JsonObject()
        .add("text", post.getText())
        .toString();
  }

  private NewPost createNewPostFrom(Request req) {
    JsonObject jsonObject = JsonObject.readFrom(req.body());
    return new NewPost(jsonObject.getString("text", ""));
  }
}
