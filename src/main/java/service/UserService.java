package service;

import DAO.UserDAO;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserService
{
    UserDAO userDAO = new UserDAO();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public User register(User user)
    {
        try
        {
            User tmpUser = new User();
            tmpUser.setUsername(user.getUsername());
            tmpUser.setPassword(user.getPassword());

            userDAO.saveOrUpdate(tmpUser);
            return tmpUser;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public User login(User user)
    {
        try
        {
            User tmpUser = userDAO.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            userDAO.saveOrUpdate(tmpUser);
            return tmpUser;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
