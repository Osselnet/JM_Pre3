package web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<User, List<List<String>>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        List<Role> allRoles = userService.getAllRoles();
        Map<User, List<List<String>>> usersWithRoles = new HashMap<>();
        allUsers.forEach(user -> usersWithRoles.put(user, userService.getUserRoles(allRoles, user)));
        return usersWithRoles;
    }

    @PostMapping
    public void addUser(@RequestBody User user, String[] roles) {
        user.setRole(userService.getRoles(roles));
        userService.insert(user);
    }

    @PutMapping
    public void editUser(@RequestBody User user, String[] roles) {
        user.setRole(userService.getRoles(roles));
        userService.update(user);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
