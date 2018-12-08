package org.openchat;

import static spark.Spark.get;

public class Routes {

  public void create() {
    swaggerRoutes();
    openChatRoutes();
  }

  private void openChatRoutes() {
    get("status", (req, res) -> "OpenChat OK");
  }

  private void swaggerRoutes() {

  }
}
