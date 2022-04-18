package com.travel.domain.archive.repository;

import com.travel.domain.archive.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    boolean existsByName(@Param(value = "name") String name);

    Place getByName(String name);
}
