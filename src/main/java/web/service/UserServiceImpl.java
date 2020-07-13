package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {this.userDao = userDao;}

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUser();
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return userDao.readRole();
    }

    @Transactional(readOnly = true)
    public Set<Role> getRoles(String[] ids) {
        return userDao.getRoles(ids);
    }

    @Transactional
    public void insert(User user) {
        userDao.add(user);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<List<String>> getUserRoles(List<Role> allRoles, User user) {
        List<List<String>> newMapUp = new ArrayList<>();
        allRoles.forEach(role -> {
            List<String> newMap = new ArrayList<>();
            newMap.add(String.valueOf(role.getId()));
            newMap.add(role.getRole());
            newMap.add(user.isRoleInUser(role) ? "true" : "false");
            newMapUp.add(newMap);
        });
        return newMapUp;
    }
}
