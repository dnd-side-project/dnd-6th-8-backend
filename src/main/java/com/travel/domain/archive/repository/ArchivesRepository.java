package com.travel.domain.archive.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EPlaces;
import com.travel.domain.archive.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface ArchivesRepository extends JpaRepository<Archives, Long> {
    @Query(value = "select * from archives where place_id = :placeId And is_share=1 order by rand() limit 5",
            nativeQuery = true)
    List<Archives> findByPlace_Id(@Param("placeId") long placeId);
}
