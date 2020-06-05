package br.com.authenticator.resource.user;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    void delete(Long id);

    User findByUserId(Long id);

    User getByUsername(String username);

    boolean isUserExist(String username);

}