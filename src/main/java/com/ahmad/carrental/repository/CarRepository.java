package com.ahmad.carrental.repository;

import com.ahmad.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
