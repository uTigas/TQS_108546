package tqs.hw1.fs_webApp.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tqs.hw1.fs_webApp.data.entity.Bus;
import tqs.hw1.fs_webApp.data.repository.BusRepository;

@SpringBootTest
public class PersistenceTest {

    @Autowired
    private BusRepository repo;
    
    private Bus bus;

    
    @BeforeEach
    void setUp(){
        bus = new Bus("Test Bus");
    }

    @Test
    void persistsAfterSaved(){
        repo.save(bus);
        assertEquals(repo.findById("Test Bus").get().getId(), bus.getId());
    }

    @AfterEach
    void undo(){
        repo.delete(bus);
    }
}
