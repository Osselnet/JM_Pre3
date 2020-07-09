package web.dao;

import web.model.Role;
import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    private EntityManager currentSession;

    public UserDaoImp(EntityManager entityManager) {
        this.currentSession = entityManager;
    }

    @Override
    public void update(User user) {
 /*       TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("update User set name = :userName where id = :userId");
        query.setParameter("userName", user.getLogin());
        query.setParameter("userId", user.getId());
        query.executeUpdate();
*/
        currentSession.merge(user);
    }

    @Override
    public void delete(long id) {
/*
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("delete User where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
*/
        currentSession.remove(getUserById(id));
    }

    @Override
    public void add(User user) {
        currentSession.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        TypedQuery<User> query = currentSession.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = currentSession.createQuery("from User where id = :userId", User.class);
        query.setParameter("userId", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = currentSession.createQuery("from User where login = :userName", User.class);
        query.setParameter("userName", name);
        return (User) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> readRole() {
        TypedQuery<Role> query = currentSession.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Role> getRoles(String[] ids) {
        TypedQuery<Role> query = currentSession.createQuery("from Role where id = :id", Role.class);
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ids).forEach(roleId -> {
            query.setParameter("id", Long.parseLong(roleId));
            roles.add(query.getSingleResult());
        });
        return roles;
    }

}
