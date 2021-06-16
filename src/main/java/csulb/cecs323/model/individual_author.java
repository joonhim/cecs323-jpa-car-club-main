package csulb.cecs323.model;

// Imported the necessary Java packages
import javax.persistence.*;

@Entity
@DiscriminatorValue("Individual Author")
public class individual_author extends authoring_entities {
    @Id
    @OneToOne
    @JoinColumn(name = "individual_authors_email", referencedColumnName = "email", nullable = false)
    private authoring_entities authoringEntities;

    public individual_author(){}

<<<<<<< Updated upstream
    public individual_author(String email){
=======
<<<<<<< HEAD
    public individual_author(String email, String name){
=======
    public individual_author(String email){
>>>>>>> 298a04b86a476d88b2cfa948dc86cafd4b2bd811
>>>>>>> Stashed changes
        super(email);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String toString(){
        return super.toString() + " Authoring Entity type: Individual Author";
    }
}
