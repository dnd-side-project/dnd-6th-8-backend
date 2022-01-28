package com.travel.domain.archive.repository;

import com.travel.domain.archive.entity.Archives;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivesRepository extends JpaRepository<Archives, Long> {
}
