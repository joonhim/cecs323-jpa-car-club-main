package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthoringEntities {
    /**
     * Variables
     */
    private String authorEntityType;
    @Id
    private String email;
    private String name;
    private String headWriter;
    private int yearFormed;

    /**
     * Constructor
     */

    public AuthoringEntities() {}

    public AuthoringEntities(String at, String e, String n, String hw, int yf) {
        this.authorEntityType = at;
        this.email = e;
        this.name = n;
        this.headWriter = hw;
        this.yearFormed = yf;
    }

    public AuthoringEntities(String authorEntityType, String email, String name, String headWriter, String yearFormed) {}

    /**
     * Getters & Setters
     */

    public String getAuthorEntityType() {
        return this.authorEntityType;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getHeadWriter() {
        return this.headWriter;
    }

    public int getYearFormed() {
        return this.yearFormed;
    }

    public void setAuthorEntityType(String at) {
        this.authorEntityType = at;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setHeadWriter(String hw) {
        this.headWriter = hw;
    }

    public void setYearFormed(int yf) {
        this.yearFormed = yf;
    }

    /**
     * Returns string values of Authoring Entities
     */

    @Override
    public String toString () {
        return "Author Type: " + this.authorEntityType + " Email: " + this.email +
                " Name: " + this.name + " Head Writer: " + this.headWriter +
                " Year Formed: " + this.yearFormed;
    }
}
