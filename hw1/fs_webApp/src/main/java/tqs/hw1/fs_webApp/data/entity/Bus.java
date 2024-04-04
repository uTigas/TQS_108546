package tqs.hw1.fs_webApp.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bus {
    
    @Id
    private String id;

    public Bus(){
    }

    public Bus(String id){
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
