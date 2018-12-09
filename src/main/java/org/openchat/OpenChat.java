package org.openchat;

import static org.eclipse.jetty.http.HttpStatus.NOT_IMPLEMENTED_501;
import static spark.Spark.before;
import static spark.Spark.internalServerError;
import static spark.Spark.notFound;
import static spark.Spark.port;

import org.openchat.api.UsersApi;
import org.openchat.domain.users.IdGenerator;
import org.openchat.domain.users.UserRepository;
import org.openchat.domain.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenChat {

  private static Logger logger  = LoggerFactory.getLogger(OpenChat.class);
  private static final String  API_NOT_IMPLEMENTED = "API not implmented";
  private static final String  INTERNAL_SERVER_ERROR = "Internal Server Error";

  private Routes routes = new Routes(
      new UsersApi(
          new UserService(
              new UserRepository(),
                new IdGenerator())));

  public void start() {
    port(4321);
    enableCors();
    routes.create();
    configureInternalServerError();
    configureNotImplemented();
  }

  private void configureNotImplemented() {
    notFound((req, res) -> {
      res.status(NOT_IMPLEMENTED_501);
      logger.error(API_NOT_IMPLEMENTED +  ": " + req.pathInfo());
      return API_NOT_IMPLEMENTED;
    });
  }

  private void configureInternalServerError() {
    internalServerError((req, res) -> {
      res.status(NOT_IMPLEMENTED_501);
      logger.error(API_NOT_IMPLEMENTED +  ": " + req.pathInfo());
      return INTERNAL_SERVER_ERROR;
    });
  }

  private void enableCors() {
    before((request, response) -> {
      response.header("Access-Control-Allow-Origin", "*");
    });
  }


}
