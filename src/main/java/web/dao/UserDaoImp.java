package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void update(User user) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("update User set name = :userName where id = :userId");
        query.setParameter("userName", user.getName());
        query.setParameter("userId", user.getId());
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("delete User where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where id = :userId");
        query.setParameter("userId", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where name = :userName");
        query.setParameter("userName", name);
        return (User) query.getSingleResult();
    }
}
