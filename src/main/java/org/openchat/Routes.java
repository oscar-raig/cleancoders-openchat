package org.openchat;

import org.openchat.api.UsersAPI;

import static spark.Spark.get;
import static spark.Spark.post;

public class Routes {

  private final UsersAPI usersAPI;

  public Routes(UsersAPI usersAPI) {
    this.usersAPI = usersAPI;
  }

  public void create() {
    swaggerRoutes();
    openChatRoutes();
  }

  private void openChatRoutes() {
    get("status", (req, res) -> "OpenChat OK");
    post("usersAPI" , (req,res) -> usersAPI.createUser(req, res));
  }

  private void swaggerRoutes() {

  }
}
