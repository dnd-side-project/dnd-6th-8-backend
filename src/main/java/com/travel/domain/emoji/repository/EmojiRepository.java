package com.travel.domain.emoji.repository;

import com.travel.domain.emoji.entity.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
}
