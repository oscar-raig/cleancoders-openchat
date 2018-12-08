package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openchat.domain.users.RegsitrationData;
import org.openchat.domain.users.UserService;
import spark.Request;
import spark.Response;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsersApiShould {

  private static final String USERNAME = "alice";

  private static final String PASSWORD = "FDFDdfd";
  private static final String ABOUT = "a comment";
  private static final RegsitrationData REGISTRATION_DATA = new RegsitrationData(USERNAME, PASSWORD, ABOUT);

  private UsersApi userApi;

  @Mock Request request;
  @Mock Response response;
  @Mock UserService userService;

  @BeforeEach void
  setUp() {
    userApi = new UsersApi(userService);
  }



  @Test
  void callcreateUserService() {

    given(request.body()).willReturn(jsonContaining(REGISTRATION_DATA));

    userApi.createUser(request, response);

    verify(userService).createUser(REGISTRATION_DATA);
  }

  private String jsonContaining(RegsitrationData registrationData) {
    return new JsonObject()
        .add("username", registrationData.getUsername())
        .add("password", registrationData.getPassword())
        .add("about", registrationData.getAbout())
        .toString();

  }
}