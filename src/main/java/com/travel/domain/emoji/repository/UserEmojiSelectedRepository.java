package com.travel.domain.emoji.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.emoji.entity.UserEmojiSelected;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserEmojiSelectedRepository extends JpaRepository<UserEmojiSelected, Long> {

    @Query("SELECT DISTINCT e.archives FROM UserEmojiSelected e WHERE e.createdAt > :sevenDays GROUP BY e.archives ORDER BY COUNT(e.archives) DESC")
    public List<Archives> orderByCount(@Param("sevenDays") LocalDateTime sevenDays, Pageable pageable);

    @Query(value = "select * from UserEmojiSelected where archiveId = :archiveId", nativeQuery = true)
    List<UserEmojiSelected> findByArchiveId(@Param("archiveId") long archiveId);
}
