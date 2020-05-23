package model;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public long getPostId()
    {
        return postId;
    }

    public void setPostId(long postId)
    {
        this.postId = postId;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}