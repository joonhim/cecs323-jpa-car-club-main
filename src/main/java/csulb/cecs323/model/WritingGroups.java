package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * A gathering of writers who meet regularly to offer constructive feedback for each other's work.
 */

/**
 * The WritingGroups class inherits the attributes and methods from authoring_entities class.
 *
 * DiscriminatorValue specifies Writing Group for the DiscriminatorColumn in authoring_entities.
 */
@Entity
@DiscriminatorValue("Writing Group")
public class WritingGroups extends authoring_entities {
    // Constructor

    public WritingGroups() {}

    /**
     * The constructor for the Writing Groups class.
     * @param email
     * @param name
     * @param head_writer
     * @param year_formed
     */
    public WritingGroups(String email, String name, String head_writer, int year_formed){
        super(email, name, head_writer, year_formed);
    }

    /**
     * Gets the email address of the Author.
     * @return Email address of the Author
     */
    @Override
    public String getEmail(){ return super.getEmail();}

    /**
     * toString() method returns the super string values of the Writing Group
     */
    @Override
    public String toString(){
        return super.toString() + " Authoring Entity Type: Writing Group";
    }

}
