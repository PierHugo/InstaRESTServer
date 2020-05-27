import controller.Controller;
import model.Comment;
import model.Post;
import model.User;

import java.util.List;

public class App
{

    public static void main(String[] args)
    {
        Controller con = new Controller();

        //user1 adds post3
        User user = con.getUserDAO().findByUsername("user1");

        Post post3 = new Post();
        post3.setImageURL("https://www.gstatic.com/webp/gallery/3.png");
        post3.setDescription("desc3");
        post3.setUser(user);
        con.getPostDAO().saveOrUpdate(post3);

        //list of user1's posts
        List<Post> posts = con.getPostDAO().findAllByUserId(user.getId());
        System.out.println(posts.get(0).toString());
        System.out.println(posts.get(1).toString());

        //user0 adds comment3 to post1
        Post post = con.getPostDAO().findById(1);

        Comment comment3 = new Comment();
        comment3.setComment("com3");
        comment3.setPost(post);
        comment3.setUser(user);
        con.getCommentDAO().saveOrUpdate(comment3);

        //list of post1's comments
        List<Comment> comments = con.getCommentDAO().findAllByPostId(post.getId());
        System.out.println(comments.get(0).toString());
        System.out.println(comments.get(1).toString());

    }
}
