package DAO;

import controller.Controller;
import model.Comment;

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

}