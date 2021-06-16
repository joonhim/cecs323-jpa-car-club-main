package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A person or a corporate body responsible for releasing printed documents to the public.
 */
@Entity
public class Publishers {
    // Variables

    /**
     * The name of the Publisher.
     */
    @Id // Primary Key
    @Column(length = 80, nullable = false)
    private String name;

    /**
     * The phone number of the Publisher.
     */
    @Column(length = 24, nullable = false)
    private String phone;

    /**
     * The email address of the Publisher.
     */
    @Column(length = 80, nullable = false)
    private String email;


    // Constructor

    public Publishers(){}

    /**
     * The constructor for the Publisher class.
     * @param name
     * @param email
     * @param phone
     */
    public Publishers(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters & Setters

    /**
     * Gets the name of the Publisher.
     * @return Name of the Publisher
     */
    public String getName(){
        return this.name;
    }

    /**
     * Changes the name of the Publisher.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the phone number of the Publisher.
     * @return Phone number of the Publisher
     */
    public String getPhone(){
        return this.phone;
    }

    /**
     * Changes the phone number of the Publisher.
     * @param phone
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * Gets the email address of the Publisher.
     * @return Email address of the Publisher
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Changes the email address of the Publisher.
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * toString() method prints the string values of the Publisher
     * @return Publisher Name, Publisher Phone, Publisher Email
     */
    @Override
    public String toString(){
        return "Publisher Name: " + this.name +
                "\nPublisher Phone: " + this.phone +
                "\nPublisher Email: " + this.email;
    }
}