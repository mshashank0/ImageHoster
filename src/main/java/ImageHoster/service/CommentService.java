package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.ImageRepository;
import ImageHoster.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Services related to comments
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //The method calls the getCommentsOn() method in the Repository and passes the image to be persisted in the database
    public List<Comment> getCommentsOn(Image image) {
        return commentRepository.getCommentsOn(image);
    }

    //The method calls the createComment() method in the Repository and passes the comment object to be persisted in the database
    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }
}