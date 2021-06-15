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

    public Publishers(String n, String e, String p){
        this.name = n;
        this.email = e;
        this.phone = p;
    }

    /* getters and setters
     */
    public String getName(){return this.name;}
    public void setName(String n){
        this.name = n;
    }

    public String getPhone(){return this.phone;}
    public void setPhone(String p){
        this.phone = p;
    }

    public String getEmail(){return this.email;}
    public void setEmail(String e){
        this.email = e;
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