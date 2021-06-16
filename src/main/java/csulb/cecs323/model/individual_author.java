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

    public individual_author(String email){
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
