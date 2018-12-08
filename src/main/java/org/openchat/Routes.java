package org.openchat;

import static spark.Spark.get;
import static spark.Spark.post;

public class Routes {

  private final Users users;

  public Routes(Users users) {
    this.users = users;
  }

  public void create() {
    swaggerRoutes();
    openChatRoutes();
  }

  private void openChatRoutes() {
    get("status", (req, res) -> "OpenChat OK");
    post("users" , (req,res) -> users.handle(req, res));
  }

  private void swaggerRoutes() {

  }
}
