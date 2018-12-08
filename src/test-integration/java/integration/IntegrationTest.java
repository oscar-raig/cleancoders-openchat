package integration;


import com.eclipsesource.json.JsonObject;
import org.junit.jupiter.api.Test;

import static com.jayway.restassured.RestAssured.given;

public class IntegrationTest {

  private String BASE_URL = "http://localhost:4321";

  @Test
  public void failing_test() {
    given()
        .body(withJsonContaining("lucy","aPassword", "abaout Lucy"))
    .when()
      .post(BASE_URL + "/users")
    .then()
      .statusCode(201);

  }

  private String withJsonContaining(String userName, String password, String about) {
    return new JsonObject()
        .add("username", userName)
        .add("password", password)
        .add("about", about).toString();
  }
}
