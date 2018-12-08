package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegsitrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserService;
import spark.Request;
import spark.Response;

import static org.eclipse.jetty.http.HttpStatus.CREATED_201;

public class UsersApi {
  private final UserService userService;

  public UsersApi(UserService userService) {
    this.userService = userService;
  }

  public String createUser(Request request, Response response) {
    User user = userService.createUser(createRegistrationDataFrom(request));
    response.status(CREATED_201);
    response.type("application/json");
    return creatingJson(user);
  }

  private String creatingJson(User user) {
    return new JsonObject()
        .add("id", user.getId())
        .add("username", user.getUsername())
        .add("about", user.getAbout()).toString();
  }

  private RegsitrationData createRegistrationDataFrom(Request request) {
    JsonObject jsonObject = Json.parse(request.body()).asObject();
    return new RegsitrationData(jsonObject.getString("username", ""),
        jsonObject.getString("password", ""),
        jsonObject.getString("about",""));
  }
}
