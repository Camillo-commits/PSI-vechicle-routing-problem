package com.akko.solver.repositories;


import com.akko.solver.models.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity,Long> {
}
