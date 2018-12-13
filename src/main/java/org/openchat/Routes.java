package org.openchat;

import static spark.Spark.get;
import static spark.Spark.post;

import org.openchat.api.LoginApi;
import org.openchat.api.PostApi;
import org.openchat.api.UsersApi;
import org.openchat.domain.posts.Clock;
import org.openchat.domain.posts.PostRepository;
import org.openchat.domain.posts.PostService;
import org.openchat.domain.users.IdGenerator;
import org.openchat.domain.users.UserRepository;
import org.openchat.domain.users.UserService;

public class Routes {

  private  UsersApi usersApi;
  private  LoginApi loginApi;
  private PostApi postApi;

  public void create() {
    createAPIs();
    swaggerRoutes();
    openChatRoutes();
  }

  private void createAPIs() {
    loginApi = new LoginApi();
    UserRepository userRepository = new UserRepository();
    IdGenerator idGenerator = new IdGenerator();
    UserService userService = new UserService(userRepository, idGenerator);
    usersApi = new UsersApi(userService);
    PostRepository postRepository = new PostRepository();
    Clock clock = new Clock();
    postApi = new PostApi(new PostService(postRepository, idGenerator, clock));
  }

  private void openChatRoutes() {
    get("status", (req, res) -> "OpenChat OK");
    post("users" , (req,res) -> usersApi.createUser(req, res));
    post("login" , (req,res) -> loginApi.login(req, res));
    post("users/:userId/timeline"  , (req,res) -> postApi.createPost(req, res));
  }

  private void swaggerRoutes() {

  }
}
