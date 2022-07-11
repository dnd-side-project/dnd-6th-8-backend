package com.travel.domain.emoji.service;

import com.travel.domain.emoji.entity.UserEmojiSelected;

public interface EmojiService {
    public UserEmojiSelected emojiCheck(long archiveId, String loginEmail, long emojiId);
    public void emojiUnCheck(Long userEmojiSelectedId);
}
