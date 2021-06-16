package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A temporary group of writers who collectively write a literary work.
 */

/**
 * The ad_hoc_team class inherits the attributes and methods from authoring_entities class.
 *
 * DiscriminatorValue specifies Ad Hoc Team for the DiscriminatorColumn in authoring_entities.
 */
@Entity
@DiscriminatorValue("Ad Hoc Team")
public class ad_hoc_team extends authoring_entities {
    // Variables

    /**
     * The email address of Ad Hoc Team.
     */
    @Column(length = 30, nullable = false)
    private String email = getEmail();

    /**
     * The type of Ad Hoc Team.
     */
    @Column(length = 80, nullable = false)
    private String type;

    // Constructor

    public ad_hoc_team(){}

    /**
     * The constructor for Ad Hoc Team class.
     * @param email
     */
    public ad_hoc_team (String email){
        super(email);
    }

    /**
     * Gets the email address of Ad Hoc Team.
     * @return Email address of Ad Hoc Team
     */
    @Override
    public String getEmail() {
        return super.getEmail();}}