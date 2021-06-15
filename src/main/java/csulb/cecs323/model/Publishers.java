package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Publishers {
    /* Variables
     */
    @Id
    private String name;
    private String phone;
    private String email;

    /* Constructor
     */
    public Publishers(){}

    public Publishers(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /* getters and setters
     */
    public String getName(){return this.name;}
    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){return this.phone;}
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){return this.email;}
    public void setEmail(String email){
        this.email = email;
    }

    /* function that returns string values of the publisher
     */
    @Override
    public String toString(){
        return "Publisher Name: " + this.name +
                "\nPublisher Phone: " + this.phone +
                "\nPublisher Email: " + this.email;
    }
}