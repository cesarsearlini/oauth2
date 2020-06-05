package br.com.authenticator.resource.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasRole('OPERATOR')")
    @GetMapping(value = "/")
    public List<User> listUser() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable(value = "id") Long id) {
        return userService.findByUserId(id);
    }

    @GetMapping(value = "/byUsername/{username}")
    public User getByUsername(@PathVariable(value = "username") String username) {
        return userService.getByUsername(username);
    }

    @PostMapping(value = "/")
    public User create(@RequestBody User user) {
        boolean userExist = userService.isUserExist(user.getUsername());
        if (!userExist) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userService.save(user);
        }
        return null;
    }

    @PutMapping(value = "/")
    public User update(@RequestBody User user) {
        boolean userExist = userService.isUserExist(user.getUsername());
        if (userExist) {
            return userService.save(user);
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
    }

    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public User changeUserPassword(@RequestBody String data) throws IOException {

        JsonNode jsonNode = objectMapper.readTree(data);

        String password = jsonNode.get("password").asText();
        String oldPassword = jsonNode.get("oldPassword").asText();

        User user =
                userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user == null || !bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            return null;
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user = userService.save(user);

        return user;
    }

}