package web.controller;

import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.UserRole;
import web.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/rest")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRole userVichRoles) {
        User user = new User(userVichRoles.getLogin(), userVichRoles.getPassword(), userVichRoles.getEmail());
        user.setRole(userService.getRoles(userVichRoles.getRoles()));
        userService.insert(user);
        return ResponseEntity.ok("User added.");
    }

    @PutMapping
    public ResponseEntity<String> editUser(@RequestBody UserRole userVichRoles) {
        User user = userService.getUser(userVichRoles.getId());
        user.setLogin(userVichRoles.getLogin());
        user.setPassword(userVichRoles.getPassword());
        user.setEmail(userVichRoles.getEmail());
        user.setRole(userService.getRoles(userVichRoles.getRoles()));
        userService.update(user);
        return ResponseEntity.ok("User changed.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Long.toString(id));
    }
}
