package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegsitrationData;
import org.openchat.domain.users.UserService;
import spark.Request;
import spark.Response;

public class UsersAPI {
  private final UserService userService;

  public UsersAPI(UserService userService) {
    this.userService = userService;
  }

  public String createUser(Request request, Response response) {
    userService.createUser(createRegistrationDataFrom(request));
    return "";
  }

  private RegsitrationData createRegistrationDataFrom(Request request) {
    JsonObject jsonObject = Json.parse(request.body()).asObject();
    return new RegsitrationData(jsonObject.getString("username", ""),
        jsonObject.getString("password", ""),
        jsonObject.getString("about",""));
  }
}
