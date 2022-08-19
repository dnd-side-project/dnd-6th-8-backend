package com.travel.domain.archive.repository;

import com.travel.domain.archive.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface ArchivesRepository extends JpaRepository<Archives, Long> {
    @Query(value = "select * from archives where place_id = :placeId And is_share=1 order by rand() limit 5",
            nativeQuery = true)
    List<Archives> findByPlace_Id(@Param("placeId") long placeId);

    @Query(value = "select * from archives where archiving_style = :archivingStyle And budget = :budget AND have_companion = :haveCompanion And is_share=1 order by rand() limit 5",
            nativeQuery = true)
    List<Archives> findByArchivingStyleAndBudgetAndHaveCompanion(@Param(value = "archivingStyle") String archivingStyle,
                                                                 @Param(value = "budget") String budget,
                                                                 @Param(value = "haveCompanion") boolean haveCompanion);
    @Query(value = "select * from archives where is_share=1 order by rand() limit 5",
            nativeQuery = true)
    List<Archives> findRandom();

    List<Archives> findByArchivingStyle(@Param(value = "archivingStyle") EArchivingStyle archivingStyle);
    List<Archives> findByIsShareAndUser_Id(boolean isShare, long userId);

    long countByUser_Id(@Param(value = "userId")long userId);

    @Query("select a.badges from Archives a where a.user.id = :userId")
    List<String> findByBadgesAndUser_Id(@Param(value = "userId")long userId);

}
