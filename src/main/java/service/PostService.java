package service;

import controller.Controller;
import model.Post;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("post")
public class PostService
{
    Controller con = new Controller();

    @GET
    @Path("/user/{user}")
//    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public List<Post> findPostsByUserId(@QueryParam("user") int userId)
    {
        try
        {
            List<Post> posts = con.getPostDAO().findAllByUserId(userId);
            return posts;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/add/{imageurl}/{description}/{user}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public boolean addPost(@QueryParam("imageurl") String imageUrl, @QueryParam("description") String description, @QueryParam("user") int userId)
    {
        Post post = new Post();
        post.setImageURL(imageUrl);
        post.setDescription(description);
        User user = con.getUserDAO().findById(userId);
        post.setUser(user);
        boolean created = con.getPostDAO().saveOrUpdate(post);
        return created;
    }

}
