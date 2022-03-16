package com.travel.domain.archive.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EPlaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArchivesRepository extends JpaRepository<Archives, Long> {

    @Query(value = "select * from Archives where place = :place order by rand() limit 5",
            nativeQuery = true)
    List<Archives> findByPlace(@Param("place")EPlaces place);
}
