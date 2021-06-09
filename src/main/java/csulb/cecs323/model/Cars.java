package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Individual, physical automobiles that someone can drive on land to transport one or more passengers
 * and a limited amount of cargo around.  Cars have four wheels and usually travel on paved roads.
 */
@Entity
@Table(name="cars")
public class Cars {

    /** The unique ID of the vehicle.  Limited to 17 characters. */
    @Id
    @Column(length = 17, nullable = false)
    private String VIN;

    /** The name of the corporation which manufactured the vehicle.  Limited to 40 characters. */
    @Column(length = 40, nullable = false)
    private String manufacturer;

    /** The popular name of the vehicle, like the Prius for Toyota.  Limited to 20 characters. */
    @Column(length = 20, nullable = false)
    private String model;

    /** The year that the vehicle was manufactured.  For now, do not worry about validating this #. */
    @Column(length = 4, nullable = false)
    private int model_year;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id", nullable = false)
    private Owners owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name", referencedColumnName = "name", nullable = false)
    private auto_body_styles autoStyle;
    @Override
    public String toString () {
        return "Cars - VIN: " + this.VIN + " Manufacturer: " + this.manufacturer +
                " Model: " + this.model + " year: " + this.model_year;


    }
    /** constructors
     */
    public Cars(){}

    public Cars(String VIN, String manufacturer, String model, int model_year, Owners owners, auto_body_styles abs){
        this.setVIN(VIN);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setModel_year(model_year);
        owner = owners;
        autoStyle = abs;
    }

    /**
     * Setters and getters
     */
    public String getVIN(){return VIN;}

    public void setVIN(String VIN){
        this.VIN = VIN;
    }

    public String getManufacturer(){return manufacturer;}

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getModel(){return model;}

    public void setModel(String model){
        this.model = model;
    }

    public int getModel_year(){return model_year;}

    public void setModel_year(int model_year){
        this.model_year = model_year;
    }

}
