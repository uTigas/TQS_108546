package tqs.hw1.fs_webApp.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Stint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Seat seat;
    @ManyToOne
    private Connection con;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Connection getCon() {
        return this.con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
