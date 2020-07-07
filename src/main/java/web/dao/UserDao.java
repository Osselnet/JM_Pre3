package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

   void update(User user);

   void delete(long id);

   void add(User user);

   List<User> getAllUser();

   User getUserById(long id);

   User getUserByName(String name);
}
