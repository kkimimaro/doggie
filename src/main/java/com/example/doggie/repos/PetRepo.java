package com.example.doggie.repos;

import com.example.doggie.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet, Integer > {
    Pet getById(Integer id);

    @Query(value = "SELECT * FROM pet WHERE activity_id = :activityId", nativeQuery = true)
    List<Pet> findPetsByActivity(@Param("activityId") Integer id);

    @Query(value = "SELECT * FROM pet WHERE poroda_id = :porodaId", nativeQuery = true)
    List<Pet> findPetsByPoroda(@Param("porodaId") Integer id);

    @Query(value = "SELECT * FROM pet order by id", nativeQuery = true)
    List<Pet> findAllOrderById();
}
