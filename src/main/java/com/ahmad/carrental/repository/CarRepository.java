package com.ahmad.carrental.repository;

import com.ahmad.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
//    @Query(value = "SELECT * FROM Car WHERE available = 1", nativeQuery = true)
    @Query("SELECT c FROM Car c where c.available = 1")
    List<Car> findAvailableCars();
}
