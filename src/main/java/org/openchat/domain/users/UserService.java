package org.openchat.domain.users;

public class UserService {

  private final UserRepository userRepository;
  private final IdGenerator idGenerator;

  public UserService(UserRepository userRepository, IdGenerator idGenerator) {
    this.userRepository = userRepository;
    this.idGenerator = idGenerator;
  }

  public User createUser(RegsitrationData registrationData) {
    User user = new User(idGenerator.next(),
        registrationData.getUsername(),
        registrationData.getAbout());
     userRepository.add(user);
     return user;
  }
}
