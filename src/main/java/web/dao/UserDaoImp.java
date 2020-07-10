package web.dao;

import web.model.Role;
import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(User user) {
 /*       TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("update User set name = :userName where id = :userId");
        query.setParameter("userName", user.getLogin());
        query.setParameter("userId", user.getId());
        query.executeUpdate();
*/
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
/*
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("delete User where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
*/
        entityManager.remove(getUserById(id));
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUser() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id = :userId", User.class);
        query.setParameter("userId", id);
        return query.getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("from User where login = :userName", User.class);
        query.setParameter("userName", name);
        return query.getSingleResult();
    }

    @Override
    public List<Role> readRole() {
        TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Set<Role> getRoles(String[] ids) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where id = :id", Role.class);
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ids).forEach(roleId -> {
            query.setParameter("id", Long.parseLong(roleId));
            roles.add(query.getSingleResult());
        });
        return roles;
    }
}
