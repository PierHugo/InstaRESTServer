package service;

import DAO.PostDAO;
import model.Post;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("post")
public class PostService
{
    PostDAO postDAO = new PostDAO();

    @POST
    @Path("/findall")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> findAll(final Post post)
    {
        try
        {
            List<Post> list = postDAO.findAll();
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
