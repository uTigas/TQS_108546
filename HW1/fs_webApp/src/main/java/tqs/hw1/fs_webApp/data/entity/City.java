package tqs.hw1.fs_webApp.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    
    @Id
    private String name;
    
    private double[] coordinates;
    
    public City (){

    }
    
    public City (String n, double[] c){
        this.name = n;
        this.coordinates = c;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

}
