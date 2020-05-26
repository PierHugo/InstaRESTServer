package DAO;

import controller.Controller;
import model.Comment;

import javax.persistence.TypedQuery;
import java.util.List;

public class CommentDAO
{
    public boolean delete(Comment entity)
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

    public boolean saveOrUpdate(Comment entity)
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
    public List<Comment> findAll()
    {
        Controller.beginTransaction();
        List<Comment> comments = (List<Comment>) Controller.getSession().createQuery("from Comment").list();
        Controller.commitTransaction();
        return comments;
    }

    public List<Comment> findAllByPostId(int postid)
    {
        Controller.beginTransaction();
        TypedQuery<Comment> query = Controller.getSession().createQuery("FROM Comment WHERE post_id = :postid", Comment.class);
        query.setParameter("postid", postid);
        List<Comment> comments = query.getResultList();
        Controller.commitTransaction();
        if (comments.size() == 0)
            return null;
        else
            return comments;
    }

    public List<Comment> findAllByUserId(int userid)
    {
        Controller.beginTransaction();
        TypedQuery<Comment> query = Controller.getSession().createQuery("FROM Comment WHERE user_id = :userid", Comment.class);
        query.setParameter("userid", userid);
        List<Comment> comments = query.getResultList();
        Controller.commitTransaction();
        if (comments.size() == 0)
            return null;
        else
            return comments;
    }

}