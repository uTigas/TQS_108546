package tqs.hw1.fs_webApp.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Terminal {
    
    @Id
    private String name;
    
    private double latitude;

    private double longitude;

    private String city;


    public Terminal (){

    }
    
    public Terminal (String n, double lat, double lon, String c){
        this.name = n;
        this.latitude = lat;
        this.longitude = lon;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getCoordinates() {
        return new double[] {this.latitude, this.longitude};
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
