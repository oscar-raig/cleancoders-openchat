package org.openchat.api;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.openchat.infrastructure.json.UserJson.jsonFor;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegsitrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserService;
import org.openchat.domain.users.exceptions.UserExistsException;
import spark.Request;
import spark.Response;

public class UsersApi {
  private final UserService userService;

  public UsersApi(UserService userService) {
    this.userService = userService;
  }

  public String createUser(Request request, Response response) {
    try {
      return prepareOkResponse(request, response);
    } catch (UserExistsException userExistsException ){
      return prepareErrorResponse(response);
    }
  }

  private String prepareErrorResponse(Response response) {
    response.status(BAD_REQUEST_400);
    return "Username already in use";
  }

  private String prepareOkResponse(Request request, Response response) {
    User user = userService.createUser(createRegistrationDataFrom(request));
    response.status(CREATED_201);
    response.type("application/json");
    return jsonFor(user);
  }

  private RegsitrationData createRegistrationDataFrom(Request request) {
    JsonObject jsonObject = Json.parse(request.body()).asObject();
    return new RegsitrationData(jsonObject.getString("username", ""),
        jsonObject.getString("password", ""),
        jsonObject.getString("about",""));
  }
}
