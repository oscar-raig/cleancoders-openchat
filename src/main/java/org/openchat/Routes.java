package org.openchat;

import static spark.Spark.get;
import static spark.Spark.post;

import org.openchat.api.UsersApi;

public class Routes {

  private final UsersApi usersApi;

  public Routes(UsersApi usersApi) {
    this.usersApi = usersApi;
  }

  public void create() {
    swaggerRoutes();
    openChatRoutes();
  }

  private void openChatRoutes() {
    get("status", (req, res) -> "OpenChat OK");
    post("usersApi" , (req,res) -> usersApi.createUser(req, res));
  }

  private void swaggerRoutes() {

  }
}
