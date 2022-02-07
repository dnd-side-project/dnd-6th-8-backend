package com.travel.domain.archive.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivesRepository extends JpaRepository<Archives, Long> {

    List<Archives> findTop5ByPlace(EPlaces place);
}
