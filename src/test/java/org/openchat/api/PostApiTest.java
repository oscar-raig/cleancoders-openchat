package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostApiTest {

  private static String USER_ID = "aUserId";

  @Mock private Request req;
  @Mock private Response res;
  @Mock private PostService postService;

  private PostApi postApi;

  @Before
  public void setUp() {
    postApi = new PostApi(postService);
    given(postService.createPost(any(String.class), any(String.class))).willReturn(createNewPost());
    given(req.body()).willReturn(jsonContaingingPost());
  }

  @Test public void
  should_return_api() {

    given(req.params("userId")).willReturn(USER_ID);

    postApi.createPost(req, res);
    verify(res).type("application/json");
    verify(res).status(201);
  }

  @Test public void
  should_delegate_in_post_service() {

    postApi.createPost(req, res);

    verify(postService, times(1)).createPost(any(String.class), any(String.class));
  }

  private Post createNewPost() {
    return new Post("text");
  }

  private String jsonContaingingPost() {
    return new JsonObject()
            .add("text", "a post")
            .toString();
  }
}