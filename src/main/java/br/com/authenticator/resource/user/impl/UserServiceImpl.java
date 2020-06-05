package br.com.authenticator.resource.user.impl;

import br.com.authenticator.resource.user.AuthSystemUserDetail;
import br.com.authenticator.resource.user.User;
import br.com.authenticator.resource.user.UserRepository;
import br.com.authenticator.resource.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        AuthSystemUserDetail authSystemUserDetail = new AuthSystemUserDetail(user);
        new AccountStatusUserDetailsChecker().check(authSystemUserDetail);

        return authSystemUserDetail;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.isUserExist(username);
    }

}