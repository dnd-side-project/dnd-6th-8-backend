package com.travel.domain.emoji.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.emoji.entity.UserEmojiSelected;
import com.travel.domain.emoji.repository.EmojiRepository;
import com.travel.domain.emoji.repository.UserEmojiSelectedRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmojiService {
    private final UserEmojiSelectedRepository userEmojiSelectedRepository;
    private final ArchivesRepository archivesRepository;
    private final EmojiRepository emojiRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserEmojiSelected emojiCheck(long ARCHIVE_ID, String loginEmail, long EMOJI_ID) {
//        Optional<Archives> archives = archivesRepository.findById(ARCHIVE_ID);
        Archives archives = archivesRepository.findById(ARCHIVE_ID).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + ARCHIVE_ID));
        Emoji emoji = emojiRepository.findById(EMOJI_ID).orElseThrow(
                () -> new IllegalArgumentException("해당 이모지가 없습니다. id = " + EMOJI_ID));

        User user = userRepository.findByEmail(loginEmail);

        UserEmojiSelected userEmojiSelected = userEmojiSelectedRepository.save(UserEmojiSelected.builder().archives(archives).user(user).emoji(emoji).build());
        return userEmojiSelected;

    }

    @Transactional
    public void emojiUnCheck(Long USER_EMOJI_SELECTED_ID) {
        UserEmojiSelected userEmojiSelected = userEmojiSelectedRepository.findById(USER_EMOJI_SELECTED_ID).orElseThrow(
                () -> new IllegalArgumentException("해당 이모지가 없습니다. id = " + USER_EMOJI_SELECTED_ID));
        ;
        userEmojiSelectedRepository.delete(userEmojiSelected);
    }
}
