package com.jcircle.firstgraphhql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jcircle.firstgraphhql.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
}
