package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * A gathering of writers who meet regularly to offer constructive feedback for each other's work.
 */
@Entity
@DiscriminatorValue("Writing Group")
public class WritingGroups extends authoring_entities {

    public WritingGroups(String email, String name, String head_writer, int year_formed){
        super(email, name, head_writer, year_formed);
    }

    // Constructor

    public WritingGroups() { }

    /**
     * 
     * @return Email address of the Author
     */
    @Override
    public String getEmail(){ return super.getEmail();}

    /**
     * toString() method prints the string values of the Writing Group
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + " Authoring Entity Type: Writing Group";
    }

}
