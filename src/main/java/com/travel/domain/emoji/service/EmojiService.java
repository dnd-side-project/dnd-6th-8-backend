package com.travel.domain.emoji.service;

import com.travel.domain.emoji.entity.UserEmojiSelected;

public interface EmojiService {
    public UserEmojiSelected emojiCheck(long ARCHIVE_ID, String loginEmail, long EMOJI_ID);
    public void emojiUnCheck(Long USER_EMOJI_SELECTED_ID);
}
