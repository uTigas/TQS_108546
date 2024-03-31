package tqs.hw1.fs_webApp.data.entity;


import java.time.LocalTime;

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
    private Long id;

    @ManyToOne
    private Bus bus;

    @ManyToOne
    private City origin;

    @ManyToOne
    private City destination;

    private double distance;

    private double price;

    private LocalTime departure;

    private LocalTime arrival;

    public Connection (){

    }
    
    public Connection (Bus bus, City origin, City destination, double price, LocalTime departure, LocalTime arrival){
        this.bus = bus;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.distance = haversine(origin.getCoordinates(), destination.getCoordinates());
    }

    public Bus getBus() {
        return this.bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public City getOrigin() {
        return this.origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return this.destination;
    }

    public void setDestination(City destination) {
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

    public LocalTime getDeparture() {
        return this.departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime getArrival() {
        return this.arrival;
    }

    public void setArrival(LocalTime arrival) {
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
