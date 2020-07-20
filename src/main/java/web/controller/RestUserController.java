package web.controller;

import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.UserRole;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestParam("user") User user, @RequestParam("roleIds") List<String> roles) {
        String[] roleIdsFromList = new String[roles.size()];
        for (int i = 0; i < roleIdsFromList.length; i++) {
            roleIdsFromList[i] = roles.get(i);
        }

        user.setRole(userService.getRoles(roleIdsFromList));
        userService.insert(user);
        return user;
    }

    /*   @PutMapping
       public User editUser(@RequestParam("user") User user, @RequestParam("roleIds") List<String> roles) {
           String[] roleIdsFromList = new String[roles.size()];
           for (int i=0; i<roleIdsFromList.length; i++) {
               roleIdsFromList[i] = roles.get(i);
           }

           user.setRole(userService.getRoles(roleIdsFromList));
           userService.update(user);
           return user;
       }
   */
    @PutMapping
    //public User editUser(@RequestBody UserRole users) {
    //public User editUser(@RequestBody User user) {
    public UserRole editUser(@RequestBody UserRole user) {
/*
        String[] roleIdsFromList = {roleIds};

        user.setRole(userService.getRoles(roleIdsFromList));
*/
        //userService.update(user);
        return user;
    }


    @DeleteMapping
    public Long deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return id;
    }
}
