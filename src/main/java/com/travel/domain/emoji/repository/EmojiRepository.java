package com.travel.domain.emoji.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.emoji.entity.UserMarkedEmoji;
import com.travel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmojiRepository extends JpaRepository<UserMarkedEmoji, Long> {
    UserMarkedEmoji findUserMarkedEmojiByArchivesAndUser(Archives archives, User user);

    @Modifying
    @Query(value = "INSERT INTO userMarkedEmoji(archive_id, user_id) VALUES(:archiveId, :userId)", nativeQuery = true)
    void emojiCheck(long archiveId, long userId);

    @Modifying
    @Query(value = "DELETE FROM userMarkedEmoji WHERE archive_id = :archiveId AND user_id = :userId", nativeQuery = true)
    void emojiUnCheck(long archiveId, long userId);
}
