package org.openchat.api;

import org.openchat.domain.users.User;
import org.openchat.infrastructure.UserJson;
import spark.Request;
import spark.Response;

public class LoginApi {
  public String login(Request req, Response res) {
    res.type("application/json");
    res.status(200);
    return UserJson.jsonFor(creatUser(req));
  }

  private User creatUser(Request req) {
    return new User("2", "A_USERNAME", "anAbout" );
  }
}
