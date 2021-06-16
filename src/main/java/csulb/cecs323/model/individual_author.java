package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.*;

/**
 * A single person who writes any literary work.
 */

/**
 * The individual_author class inherits the attributes and methods from authoring_entities class.
 *
 * DiscriminatorValue specifies Individual Author for the DiscriminatorColumn in authoring_entities.
 */
@Entity
@DiscriminatorValue("Individual Author")
public class individual_author extends authoring_entities {
    /**
     * Has a one-to-one multiplicity.
     * individual_authors_email is the Primary Key.
     */
    @Id // Primary Key
    @OneToOne
    @JoinColumn(name = "individual_authors_email", referencedColumnName = "email", nullable = false)
    private authoring_entities authoringEntities;

    // Constructor

    public individual_author(){}

    /**
     * The constructor for the individual_author class.
     * @param email
     */
    public individual_author(String email){
        super(email);
    }

    /**
     * Gets the email address of Individual Author.
     * @return Email address of Individual Author
     */
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    /**
     * toString() method returns the string values of individual_author
     */
    @Override
    public String toString(){
        return super.toString() + " Authoring Entity type: Individual Author";
    }
}
