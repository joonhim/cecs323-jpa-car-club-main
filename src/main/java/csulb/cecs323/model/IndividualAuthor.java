package csulb.cecs323.model;

import javax.persistence.Entity;

@Entity
public class IndividualAuthor extends authoring_entities {
    private String email = getEmail();
    private String type;

    public IndividualAuthor(){}
    public IndividualAuthor(String email, String name, String type){
        super(email, name);
        this.type = type;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

}
