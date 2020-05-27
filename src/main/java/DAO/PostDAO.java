package DAO;

import controller.Controller;
import model.Post;

import javax.persistence.TypedQuery;
import java.util.List;

public class PostDAO
{
    public Post findById(int postid)
    {
        Controller.beginTransaction();
        TypedQuery<Post> query = Controller.getSession().createQuery("FROM Post WHERE id = :postid", Post.class);
        query.setParameter("postid", postid);
        List<Post> posts = query.getResultList();
        Controller.commitTransaction();
        if (posts.size() == 0)
            return null;
        else
            return posts.get(0);
    }

    public boolean saveOrUpdate(Post entity)
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

    public List<Post> findAllByUserId(int userId)
    {
        Controller.beginTransaction();
        TypedQuery<Post> query = Controller.getSession().createQuery("FROM Post WHERE user_id = :userId", Post.class);
        query.setParameter("userId", userId);
        List<Post> posts = query.getResultList();
        Controller.commitTransaction();
        if (posts.size() == 0)
            return null;
        else
            return posts;
    }

}