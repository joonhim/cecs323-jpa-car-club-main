package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.*;

/**
 * A parent class for Writing Group, Ad Hoc Team and Individual Author.
 */

/**
 * Establishes an Entity Inheritance to map class hierarchies on the database.
 *
 * Uses a discriminator column called DTYPE to differentiate all the entities in the same table.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authoring_entity_type", discriminatorType = DiscriminatorType.STRING)
public class authoring_entities {
    // Variables

    /**
     * The email address of the Authoring Entity.
     */
    @Id // Primary Key
    @Column(length = 30, nullable = false)
    private String email;

    /**
     * The name of the Authoring Entity.
     */
    @Column(length = 80, nullable = false)
    private String name;

    /**
     * The name of the Head Writer.
     */
    @Column(length = 80, nullable = false)
    private String head_writer;

    /**
     * The year the Authoring Entity is formed.
     */
    @Column(length = 4, nullable = false)
    private int year_formed;


    // Constructor

    public authoring_entities() {}

    /**
     * The constructor for authoring_entities class.
     * @param email
     * @param name
     * @param head_writer
     * @param year_formed
     */
    public authoring_entities(String email, String name, String head_writer, int year_formed) {
        this.email = email;
        this.name = name;
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }

    /**
     * For individual_author & ad_hoc_team since those classes are only using the email of authoring_entities.
     * @param email
     */
    public authoring_entities(String email){
        this.email = email;
    }


    // Getters & Setters

    /**
     * Gets the email address of the Authoring Entity.
     * @return Email address of the Authoring Entity
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the name of the Authoring Entity
     * @return Name of the Authoring Entity
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name of the Head Writer
     * @return Name of the Head Writer
     */
    public String getHeadWriter() {
        return this.head_writer;
    }

    /**
     * Gets the year the Authoring Entity is formed.
     * @return Year the Authoring Entity is formed
     */
    public int getYearFormed() {
        return this.year_formed;
    }

    /**
     * Changes the email address of the Authoring Entity.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Changes the name of the Authoring Entity.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the name of the Head Writer.
     * @param head_writer
     */
    public void setHeadWriter(String head_writer) {
        this.head_writer = head_writer;
    }

    /**
     * Changes the year the Authoring Entity is formed.
     * @param year_formed
     */
    public void setYearFormed(int year_formed) {
        this.year_formed = year_formed;
    }

    /**
     * toString() method prints the string value of the authoring_entities
     * @return Email, Name, Head Writer, Year Formed
     */
    @Override
    public String toString () {
        return  " Email: " + this.email +
                " Name: " + this.name + " Head Writer: " + this.head_writer +
                " Year Formed: " + this.year_formed;
    }
}
