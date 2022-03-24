package com.travel.domain.emoji.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.emoji.repository.EmojiRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private final EmojiRepository emojiRepository;
    private final UserRepository userRepository;

    @Transactional
    public void emojiCheck(long archiveId, String loginEmail) {
        User user = userRepository.findByEmail(loginEmail);
        emojiRepository.emojiCheck(archiveId, user.getId());
    }

    @Transactional
    public void emojiUnCheck(long archivedId, String loginEmail) {
        User user = userRepository.findByEmail(loginEmail);
        emojiRepository.emojiUnCheck(archivedId, user.getId());
    }
}
