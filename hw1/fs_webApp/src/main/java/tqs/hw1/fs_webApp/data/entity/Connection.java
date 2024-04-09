package tqs.hw1.fs_webApp.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Connection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    
    @ManyToOne
    private Bus bus;
    
    @ManyToOne
    private Terminal origin;
    
    @ManyToOne
    private Terminal destination;
    
    private double distance;
    
    private double price;
    
    private long departure;
    
    private long arrival;
    
    public Connection (){
        
    }
    
    public Connection (Bus bus, Terminal origin, Terminal destination, double price, long departure, long arrival){
        this.bus = bus;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.distance = haversine(origin.getCoordinates(), destination.getCoordinates());
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bus getBus() {
        return this.bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Terminal getOrigin() {
        return this.origin;
    }

    public void setOrigin(Terminal origin) {
        this.origin = origin;
    }

    public Terminal getDestination() {
        return this.destination;
    }

    public void setDestination(Terminal destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDeparture() {
        return this.departure;
    }

    public void setDeparture(long departure) {
        this.departure = departure;
    }

    public long getArrival() {
        return this.arrival;
    }

    public void setArrival(long arrival) {
        this.arrival = arrival;
    }

    public static double haversine(double[] coord1, double[] coord2){
        double lat1 = coord1[0];
        double lon1 = coord1[1];
        double lat2 = coord2[0];
        double lon2 = coord2[1];

        // Degrees to Radian coeff
        double coeff = Math.PI/180;

        double lat1_rad = coeff * lat1;
        double lon1_rad = coeff * lon1;
        double lat2_rad = coeff * lat2;
        double lon2_rad = coeff * lon2;
        
        // Haversine formula
        double dlon = lon2_rad - lon1_rad;
        double dlat = lat2_rad - lat1_rad;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1_rad) * Math.cos(lat2_rad) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        // Radius of the Earth in kilometers
        double R = 6371.0;
        double distance = R * c;
        return distance;
    }
}
