package DAO;

import controller.Controller;
import model.Post;

import java.util.List;

public class PostDAO
{
    public boolean delete(Post entity)
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

    public boolean saveOrUpdate(Post entity)
    {
        try
        {
            Controller.getSession().saveOrUpdate(entity);
            Controller.commitTransaction();
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Post> findAll()
    {
        Controller.beginTransaction();
        List<Post> posts = (List<Post>) Controller.getSession().createQuery("from Post").list();
        Controller.commitTransaction();
        return posts;
    }

}