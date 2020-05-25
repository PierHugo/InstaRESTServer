package service;

import DAO.UserDAO;
import model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
public class UserService
{
    UserDAO userDAO = new UserDAO();

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public User register(final User user)
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

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(final User user)
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

    @POST
    @Path("/findall")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll(final User user)
    {
        try
        {
            List<User> list = userDAO.findAll();
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
