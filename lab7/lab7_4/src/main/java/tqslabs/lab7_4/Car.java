package tqslabs.lab7_4;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="car")
public class Car{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String maker;
    private String model;

    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Car(){
        
    }
    
    public Car(String maker, String model){
        this.maker = maker;
        this.model = model;
    }
    
    public boolean equals(Object obj){
        return true;
    }
    
    public int hashCode(){
        return 1;
    }
  
    public Long getCarId() {
        return this.carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return this.maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public String toString(){
        return "";
    }
}
