package org.openchat.domain.users;

import java.net.UnknownServiceException;
import org.openchat.domain.users.exceptions.UserExistsException;

public class UserService {

  private final UserRepository userRepository;
  private final IdGenerator idGenerator;

  public UserService(UserRepository userRepository, IdGenerator idGenerator) {
    this.userRepository = userRepository;
    this.idGenerator = idGenerator;
  }

  public User createUser(RegsitrationData registrationData) {
    if(userRepository.isUsernameTaken(registrationData.getUsername())) {
      throw new UserExistsException();
    }
    User user = new User(idGenerator.next(),
        registrationData.getUsername(),
        registrationData.getAbout());
     userRepository.add(user);
     return user;
  }
}
