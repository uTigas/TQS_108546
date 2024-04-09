package tqs.hw1.fs_webApp.data.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    private long timestamp;

    private double totalPrice;

    
    
    @OneToMany
    private List<Stint> stints;
    
    
    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Stint> getStints() {
        return this.stints;
    }

    public void setStints(List<Stint> stints) {
        this.stints = stints;
        for (Stint st : stints) {
            this.totalPrice += st.getCon().getPrice();
        }
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }

}
