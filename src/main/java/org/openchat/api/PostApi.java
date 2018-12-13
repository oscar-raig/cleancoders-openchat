package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.openchat.domain.posts.InapropiateLanguageException;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import org.openchat.infrastructure.json.PostJson;
import spark.Request;
import spark.Response;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
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
    try {
      Post post = postService.createPost(userId, getTextFrom(req));
      return PostJson.jsonPost(post);
    } catch(InapropiateLanguageException e) {
      res.status(BAD_REQUEST_400);
      return "Post contains inapropiate language";
    }
  }

  private String getTextFrom(Request req) {
    JsonObject jsonObject = JsonObject.readFrom(req.body());
    return jsonObject.getString("text", "");
  }
}
