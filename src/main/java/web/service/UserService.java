package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();

    List<Role> getAllRoles();

    Set<Role> getRoles(String[] ids);

    void insert(User user);

    void update(User user);

    User getUser(Long id);

    void deleteUser(Long id);
}
