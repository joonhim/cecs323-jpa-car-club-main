package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authoring_entity_type", discriminatorType = DiscriminatorType.STRING)
public class authoring_entities {
    /**
     * Variables
     */
    @Id
    private String email;
    private String name;
    private String head_writer;
    private int year_formed;


    /**
     * Constructor
     */

    public authoring_entities() {}

    public authoring_entities(String e, String n, String hw, int yf) {
        this.email = e;
        this.name = n;
        this.head_writer = hw;
        this.year_formed = yf;
    }

    public authoring_entities(String email, String name){
        this.email = email;
        this.name = name;
    }


    /**
     * Getters & Setters
     */

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getHeadWriter() {
        return this.head_writer;
    }

    public int getYearFormed() {
        return this.year_formed;
    }


    public void setEmail(String e) {
        this.email = e;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setHeadWriter(String hw) {
        this.head_writer = hw;
    }

    public void setYearFormed(int yf) {
        this.year_formed = yf;
    }

    /**
     * Returns string values of Authoring Entities
     */

    @Override
    public String toString () {
        return  " Email: " + this.email +
                " Name: " + this.name + " Head Writer: " + this.head_writer +
                " Year Formed: " + this.year_formed;
    }
}
