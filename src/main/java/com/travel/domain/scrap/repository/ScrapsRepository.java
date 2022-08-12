package com.travel.domain.scrap.repository;

import com.travel.domain.scrap.entity.Scraps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapsRepository extends JpaRepository<Scraps, Long> {
    @Query(value = "select * from scraps where user_id = :userId",
            nativeQuery = true)
    List<Scraps> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from scraps where user_id = :userId and archive_id = :archiveId", nativeQuery = true)
    Scraps findByUserAndArchive(@Param("userId") long userId, @Param("archiveId") long archiveId);
}
