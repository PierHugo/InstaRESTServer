package service;

import DAO.CommentDAO;
import model.Comment;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("comment")
public class CommentService
{
    CommentDAO commentDAO = new CommentDAO();

    @POST
    @Path("/findall")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> findAll(final Comment comment)
    {
        try
        {
            List<Comment> list = commentDAO.findAll();
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
