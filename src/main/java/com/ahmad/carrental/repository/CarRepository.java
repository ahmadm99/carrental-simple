package com.ahmad.carrental.repository;

import com.ahmad.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
//    @Query(value = "SELECT * FROM Car WHERE available = 1", nativeQuery = true)
    @Query("SELECT c FROM Car c where c.available = 1")
    List<Car> findAvailableCars();

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("select w from Wallet w where w.id = :id")
//    Car findOneForUpdate(@Param("id") Long id);
}
