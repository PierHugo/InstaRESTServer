import controller.Controller;
import model.Post;
import model.User;

import java.util.List;

public class App
{

    public static void main(String[] args)
    {
        Controller con = new Controller();

/*
        //Return list of all users
        List<User> users = con.getUserDAO().findAll();
        System.out.println(users.get(0).toString());
        System.out.println(users.get(1).toString());
*/

        //Return posts of user0
        User user = Controller.getUserDAO().findByUsername("user0");
        List<Post> posts = Controller.getPostDAO().findAllByUserId(user.getUserId());
        System.out.println(posts.get(0).toString());

    }
}
