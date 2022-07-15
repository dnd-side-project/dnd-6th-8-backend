package com.travel.domain.emoji.service;

import com.travel.domain.emoji.dto.EmojiListResponseDto;
import com.travel.domain.emoji.entity.UserEmojiSelected;

import java.util.List;

public interface EmojiService {
    public UserEmojiSelected emojiCheck(long archiveId, String loginEmail, long emojiId);
    public void emojiUnCheck(Long userEmojiSelectedId);
    public List<EmojiListResponseDto> getEmojisListOfArchives(long archiveId);
}
