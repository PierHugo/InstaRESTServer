package service;

import controller.Controller;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserService
{
    Controller con = new Controller();

    @GET
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_XML)
    public User login(@PathParam("username") String username, @PathParam("password") String password)
    {
        try
        {
            User tmpUser = con.getUserDAO().findByUsernameAndPassword(username, password);
            return tmpUser;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/register/{username}/{password}")
    @Produces(MediaType.APPLICATION_XML)
    public boolean register(@PathParam("username") String username, @PathParam("password") String password)
    {
        User tmpUser = new User();
        tmpUser.setUsername(username);
        tmpUser.setPassword(password);

        boolean created = con.getUserDAO().saveOrUpdate(tmpUser);
        return created;
    }

}
