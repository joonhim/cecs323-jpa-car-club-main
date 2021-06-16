package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@DiscriminatorValue("Ad Hoc Team")
public class ad_hoc_team extends authoring_entities {
    private String email = getEmail();
    private String type;

    public ad_hoc_team(){}
    public ad_hoc_team (String email){
        super(email);
    }

    @Override
    public String getEmail() {
        return super.getEmail();}}