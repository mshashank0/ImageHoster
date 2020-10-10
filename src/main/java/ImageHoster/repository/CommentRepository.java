package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//All the database related queries for the comments table
@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment createComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            //Save new comment object to "comments" table
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch all comments from the database posted on image
    //Returns list of comments in case comments are found in the database
    //Returns null if no comment is found in the database
    public List<Comment> getCommentsOn(Image image) {
        EntityManager em = emf.createEntityManager();
        try {
            // Query to fetch comments posted on image by all the users
            // Sorted teh result cased on created date in descending order to get latest comment at top
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c FROM Comment c WHERE c.image.id =:imageId ORDER BY c.createdDate DESC", Comment.class)
                    .setParameter("imageId", image.getId());
            return typedQuery.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
}