package com.travel.domain.emoji.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.emoji.entity.UserEmojiSelected;
import com.travel.domain.emoji.repository.UserEmojiSelectedRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private final UserEmojiSelectedRepository userEmojiSelectedRepository;
    private final ArchivesRepository archivesRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long emojiCheck(long archiveId, String loginEmail, Emoji emoji) {
        Optional<Archives> archives = archivesRepository.findById(archiveId);
        User user = userRepository.findByEmail(loginEmail);

        UserEmojiSelected userEmojiSelected = new UserEmojiSelected();
        userEmojiSelected.builder().archives(archives.get()).user(user).emoji(emoji).build();

        userEmojiSelectedRepository.save(userEmojiSelected);
        return userEmojiSelected.getId();

    }

    @Transactional
    public void emojiUnCheck(long userEmojiSelectedId, String loginEmail) {
        User user = userRepository.findByEmail(loginEmail);

        UserEmojiSelected userEmojiSelected = userEmojiSelectedRepository.findById(userEmojiSelectedId).get();
        userEmojiSelectedRepository.delete(userEmojiSelected);
    }
}
