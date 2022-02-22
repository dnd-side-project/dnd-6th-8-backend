package com.travel.domain.scrap.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapsRepository extends JpaRepository<Scraps, Long> {
    @Query("select s from Scraps as s where s.archives.id=:archiveId")
    List<Scraps> findByArchivesAndScrapDateTime(Long archiveId);
}
