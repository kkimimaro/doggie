package com.example.doggie.repos;

import com.example.doggie.domain.Poroda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PorodaRepo extends JpaRepository<Poroda, Integer > {

    @Query(value = "SELECT * FROM poroda order by id", nativeQuery = true)
    List<Poroda> findAllOrderById();

    Poroda getById(Integer id);
}
