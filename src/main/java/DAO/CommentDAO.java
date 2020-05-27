package DAO;

import controller.Controller;
import model.Comment;

import javax.persistence.TypedQuery;
import java.util.List;

public class CommentDAO
{
    public Comment findById(int commentid)
    {
        Controller.beginTransaction();
        TypedQuery<Comment> query = Controller.getSession().createQuery("FROM Comment WHERE id = :commentid", Comment.class);
        query.setParameter("commentid", commentid);
        List<Comment> comments = query.getResultList();
        Controller.commitTransaction();
        if (comments.size() == 0)
            return null;
        else
            return comments.get(0);
    }

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

    public List<Comment> findAllByPostId(int postId)
    {
        Controller.beginTransaction();
        TypedQuery<Comment> query = Controller.getSession().createQuery("FROM Comment WHERE post_id = :postId", Comment.class);
        query.setParameter("postId", postId);
        List<Comment> comments = query.getResultList();
        Controller.commitTransaction();
        if (comments.size() == 0)
            return null;
        else
            return comments;
    }

}