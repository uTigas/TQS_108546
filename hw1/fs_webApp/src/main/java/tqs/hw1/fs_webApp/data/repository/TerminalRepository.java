package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Terminal;

@Repository
public interface TerminalRepository extends CrudRepository<Terminal, String>{
    List<Terminal> findAll();
    List<Terminal> findByCity(String city);
}
