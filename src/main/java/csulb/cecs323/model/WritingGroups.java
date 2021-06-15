package csulb.cecs323.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Writing Group")
public class WritingGroups extends authoring_entities {

    public WritingGroups(String email, String name, String head_writer, int year_formed){
        super(email, name, head_writer, year_formed);
    }

    public WritingGroups() { }

    @Override
    public String getEmail(){ return super.getEmail();}

    @Override
    public String toString(){
        return super.toString() + " Authoring Entity Type: Writing Group";
    }

}
