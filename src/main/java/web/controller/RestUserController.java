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

    @GetMapping(value = "users")
    public Map<User, List<List<String>>> usersGet() {
        List<User> allUsers = userService.getAllUsers();
        List<Role> allRoles = userService.getAllRoles();
        Map<User, List<List<String>>> usersWithRoles = new HashMap<>();
        allUsers.forEach(user -> usersWithRoles.put(user, userService.getUserRoles(allRoles, user)));
        return usersWithRoles;
    }

    @PostMapping(value = "add")
    public Map<User, String> addPost(@RequestBody User user) {
        user.setRole(userService.getRoles(roleIds));
        userService.insert(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "edit/{id}")
    public String editPost(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "delete/{id}")
    public void deletePost(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
