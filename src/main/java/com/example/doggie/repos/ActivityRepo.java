package com.example.doggie.repos;

import com.example.doggie.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Integer > {

    @Query(value = "SELECT * FROM activity order by id", nativeQuery = true)
    List<Activity> findAllOrderById();

    Activity getById(Integer id);
}
