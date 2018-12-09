package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.users.RegsitrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserService;
import org.openchat.infrastructure.UserBuilder;
import spark.Request;
import spark.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsersApiTest {

  private static final String USERNAME = "alice";

  private static final String PASSWORD = "FDFDdfd";
  private static final String ABOUT = "a comment";
  private static final RegsitrationData REGISTRATION_DATA = new RegsitrationData(USERNAME, PASSWORD, ABOUT);
  private static final String AN_ID = "1";
  private static final User USER = UserBuilder.aUser()
      .withId(AN_ID)
      .withUsername(USERNAME)
      .withAbout(ABOUT)
      .build();


  private UsersApi userApi;

  @Mock Request request;
  @Mock Response response;
  @Mock UserService userService;

  @Before public void
  setUp() {
    userApi = new UsersApi(userService);
    given(request.body()).willReturn(jsonContaining(REGISTRATION_DATA));
    given(userService.createUser(REGISTRATION_DATA)).willReturn(USER);

  }



  @Test
  public void callcreateUserService() {

    userApi.createUser(request, response);

    verify(userService).createUser(REGISTRATION_DATA);
  }

  @Test
  public void return_json_representing_a_user() {

    String result = userApi.createUser(request, response);

    JsonObject json = Json.parse(result).asObject();

    verify(response).type("application/json");
    verify(response).status(201);
    assertThat(json.get("username").asString(), Is.is(USERNAME));
    assertThat(json.get("about").asString(), Is.is(ABOUT));
    assertThat(json.get("id").asString(), Is.is(AN_ID));
  }

  

  private String jsonContaining(RegsitrationData registrationData) {

    return new JsonObject()
        .add("username", registrationData.getUsername())
        .add("password", registrationData.getPassword())
        .add("about", registrationData.getAbout())
        .toString();
  }
}