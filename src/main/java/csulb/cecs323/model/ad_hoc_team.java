package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ad_hoc_team extends authoring_entities {
    private String email = getEmail();
    private String type;

    public ad_hoc_team(){}
    public ad_hoc_team (String email, String name, String type){
        super(email, name);
        this.type = type;
    }

    @Override
    public String getEmail() {
        return super.getEmail();}}