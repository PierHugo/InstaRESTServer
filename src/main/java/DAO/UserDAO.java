package DAO;

import controller.Controller;
import model.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO
{
    public User findByUserId(int userId)
    {
        Controller.beginTransaction();
        TypedQuery<User> query = Controller.getSession().createQuery("FROM User WHERE user_id = :userId", User.class);
        query.setParameter("userId", userId);
        List<User> users = query.getResultList();
        Controller.commitTransaction();
        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

    public User findByUsername(String username)
    {
        Controller.beginTransaction();
        TypedQuery<User> query = Controller.getSession().createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        Controller.commitTransaction();
        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

    public User findByUsernameAndPassword(String username, String password)
    {
        Controller.beginTransaction();
        TypedQuery<User> query = Controller.getSession().createQuery("FROM User WHERE username = :username AND password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        Controller.commitTransaction();
        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

    public boolean delete(User entity)
    {
        try
        {
            Controller.beginTransaction();
            if (!Controller.getSession().contains(entity))
                Controller.getSession().merge(entity);
            Controller.getSession().delete(entity);
            Controller.commitTransaction();
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public boolean saveOrUpdate(User entity)
    {
        try
        {
            Controller.beginTransaction();
            Controller.getSession().saveOrUpdate(entity);
            Controller.commitTransaction();
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll()
    {
        Controller.beginTransaction();
        List<User> users = (List<User>) Controller.getSession().createQuery("from User").list();
        Controller.commitTransaction();
        return users;
    }
}