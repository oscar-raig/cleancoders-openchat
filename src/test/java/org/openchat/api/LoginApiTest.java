package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginApiTest {
  private static final String A_USERNAME = "A_USERNAME";
  private static final String USERNAME = "username";
  private static final String AN_ID = "2";
  private static final String AN_ABOUT = "anAbout";
  private LoginApi loginApi;

  @Mock Response res;
  @Mock Request req;

  @Before
  public void setUp() {
    loginApi = new LoginApi();
  }

  @Test public void
  should_return_ok_when_validate() {

    given(req.body()).willReturn(loginResponse());

    String result = loginApi.login(req, res);

    verify(res).status(200);
    verify(res).type("application/json");

    assertThat(result).isEqualTo(jsonContaintinUser());
  }

  private String jsonContaintinUser() {
    JsonObject json =   new JsonObject()
        .add("id", AN_ID)
        .add(USERNAME, A_USERNAME)
        .add("about", AN_ABOUT);

    return json.toString();
  }

  private String loginResponse() {
    JsonObject json =  new JsonObject()
                .add(USERNAME, A_USERNAME)
                .add("password", "DFDFDFd");
    return json.toString();
  }
}