package org.openchat.domain.users;

import java.util.ArrayList;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserRepository {

  private List<User> users = new ArrayList<>();

  public void add(User user) {
    users.add(user);
  }

  public boolean isUsernameTaken(String username) {
    return users
        .stream()
        .anyMatch(user -> user.getUsername().equals(username));
  }
}
