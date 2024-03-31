package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, String>{
    List<City> findAll();
}
