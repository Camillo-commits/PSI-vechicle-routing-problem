package com.akko.solver.repositories;


import com.akko.solver.models.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity,Long> {

   public Optional<LocationEntity> findById(Long id);
}
