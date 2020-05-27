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

    public boolean saveOrUpdate(Comment entity)
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