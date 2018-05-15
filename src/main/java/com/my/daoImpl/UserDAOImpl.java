package com.my.daoImpl;

import com.my.dao.UserDAO;
import com.my.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User addUser(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
            return user;
        } catch (Exception ex) {
            throw new RuntimeException("cannot add user"+ex.getMessage());
        }
    }


    public List<User> getUsers() {

        List<User> userList=sessionFactory.getCurrentSession()
                .createQuery("FROM User", User.class)
                .getResultList();
        if(userList==null|| userList.size()==0){
            throw new RuntimeException("cannot find userlist.");
        }
        return userList;
    }

    public User getUser(String userName) {
        try {
            User user = sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM User where userName=:userName", User.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
            return user;
        }
        catch(Exception e){
                throw  new RuntimeException(e.getMessage());
            }
    }

    @Override
    public User getUserByEmailId(String email) {
        try {
            User user1 = sessionFactory.getCurrentSession().
                    createQuery("FROM User WHERE email=:email", User.class).
                    setParameter("email", email).
                    getSingleResult();
            return user1;
        } catch (Exception e) {
            return null;
        }
    }
}
