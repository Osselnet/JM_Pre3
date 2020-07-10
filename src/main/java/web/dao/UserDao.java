package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

   void update(User user);

   void delete(long id);

   void add(User user);

   List<User> getAllUser();

   User getUserById(long id);

   User getUserByName(String name);

   List<Role> readRole();

   Set<Role> getRoles(String[] ids);
}
