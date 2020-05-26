package DAO;

import controller.Controller;
import model.Post;

import javax.persistence.TypedQuery;
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

    public List<Post> findAllByUserId(int userid)
    {
        Controller.beginTransaction();
        TypedQuery<Post> query = Controller.getSession().createQuery("FROM Post WHERE user_id = :userid", Post.class);
        query.setParameter("userid", userid);
        List<Post> posts = query.getResultList();
        Controller.commitTransaction();
        if (posts.size() == 0)
            return null;
        else
            return posts;
    }

}