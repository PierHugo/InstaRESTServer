package service;

import DAO.CommentDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import controller.Controller;
import model.Comment;
import model.Post;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("comment")
public class CommentService
{
    CommentDAO commentDAO = new CommentDAO();
    PostDAO postDAO = new PostDAO();
    UserDAO userDAO = new UserDAO();

    @GET
    @Path("/list/{post}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public List<Comment> findCommentsByPostId(@QueryParam("post") int postId)
    {
        try
        {
            List<Comment> comments = Controller.getCommentDAO().findAllByPostId(postId);
            return comments;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/add/{comment}/{post}/{user}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public boolean addComment(@QueryParam("comment") String commentStr, @QueryParam("post") int postId, @QueryParam("user") int userId)
    {
        Comment comment = new Comment();
        Post post = postDAO.findById(postId);
        User user = userDAO.findById(userId);
        comment.setUser(user);
        comment.setPost(post);
        comment.setComment(commentStr);
        boolean created = commentDAO.saveOrUpdate(comment);
        return created;
    }

}
