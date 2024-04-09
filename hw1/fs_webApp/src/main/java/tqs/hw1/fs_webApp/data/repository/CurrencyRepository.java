package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String>{
    List<Currency> findAll();
}
