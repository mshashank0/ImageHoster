package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments'
    //Fetches user object that is saved in session
    //Fetches the image with the corresponding id from the database and adds it to the new comment model
    //Sets comment test and current date to the comment model
    //Calls createComment() service to persist the newly posted comment in Database
    //Finally redirects 'images/id/imageTitle' file wherein you fill all the updated details of the image with comments
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String postComment(@PathVariable(name = "imageId") Integer imageId, @PathVariable(name = "imageTitle") String imageTitle, String comment, Comment newComment, Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);

        //Add values in comment model object
        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setText(comment);
        newComment.setCreatedDate(new Date());

        //Create comment in "comments" table
        commentService.createComment(newComment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}