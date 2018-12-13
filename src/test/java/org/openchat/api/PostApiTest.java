package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import java.util.Date;
import java.util.UUID;
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

  private static final String A_POST_ID = UUID.randomUUID().toString();
  private static final String A_TEXT = "text";
  private final Date DATE = new Date();

  private static String USER_ID = "aUserId";
  private final Post POST = createNewPost();

  @Mock private Request req;
  @Mock private Response res;
  @Mock private PostService postService;

  private PostApi postApi;

  @Before
  public void setUp() {
    postApi = new PostApi(postService);
    given(postService.createPost(any(String.class), any(String.class)))
        .willReturn(POST);
    given(req.body()).willReturn(jsonContaingingPost(POST));
    given(req.params(any(String.class))).willReturn(USER_ID);
  }

  @Test public void
  should_return_api() {


    postApi.createPost(req, res);
    verify(res).type("application/json");
    verify(res).status(201);
  }

  @Test public void
  should_delegate_in_post_service() {


    postApi.createPost(req, res);

    verify(postService, times(1))
        .createPost(USER_ID, A_TEXT);
  }

  private Post createNewPost() {
    return new Post(A_POST_ID, USER_ID, A_TEXT, DATE);
  }

  private String jsonContaingingPost(Post post) {
    return new JsonObject()
            .add("postId", post.getPostId())
            .add("userId", post.getUserId())
            .add("text", post.getText())
            .add("dateTime", post.getDate().toString())
            .toString();
  }
}