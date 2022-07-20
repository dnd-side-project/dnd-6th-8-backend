package com.travel.domain.emoji.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.emoji.dto.EmojiListResponseDto;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.emoji.entity.UserEmojiSelected;
import com.travel.domain.emoji.repository.EmojiRepository;
import com.travel.domain.emoji.repository.UserEmojiSelectedRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService {

    private final UserEmojiSelectedRepository userEmojiSelectedRepository;
    private final ArchivesRepository archivesRepository;
    private final EmojiRepository emojiRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserEmojiSelected emojiCheck(long archiveId, String loginEmail, long emojiId) {
        Archives archives = archivesRepository.findById(archiveId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + archiveId));
        Emoji emoji = emojiRepository.findById(emojiId).orElseThrow(
                () -> new IllegalArgumentException("해당 이모지가 없습니다. id = " + emojiId));

        User user = userRepository.findByEmail(loginEmail);

        UserEmojiSelected userEmojiSelected = userEmojiSelectedRepository.save(UserEmojiSelected.builder().archives(archives).user(user).emoji(emoji).build());
        return userEmojiSelected;

    }

    @Transactional
    public void emojiUnCheck(Long userEmojiSelectedId) {
        UserEmojiSelected userEmojiSelected = userEmojiSelectedRepository.findById(userEmojiSelectedId).orElseThrow(
                () -> new IllegalArgumentException("해당 이모지가 없습니다. id = " + userEmojiSelectedId));
        ;
        userEmojiSelectedRepository.delete(userEmojiSelected);
    }

    @Transactional
    public List<EmojiListResponseDto> getEmojisListOfArchives(long archiveId, String loginEmail) {
        Archives archives = archivesRepository.findById(archiveId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + archiveId));
        List<UserEmojiSelected> filtered = userEmojiSelectedRepository.findByArchiveId(archiveId);
        List<Emoji> emojis = emojiRepository.findAll();
        User user = userRepository.findByEmail(loginEmail);

        List<Long> emojiIds = new ArrayList<>();
        emojis.stream().forEach(e -> emojiIds.add(e.getId()));

        List<HashMap> emojisList = new ArrayList<>();

        for(Long id:emojiIds)
        {
            HashMap<String, String> emojisMap = new HashMap<>();

            emojisMap.put("emojiId", String.valueOf(id));

            long emojiCount = filtered.stream().filter(e -> e.getEmoji().getId()==id).count();
            emojisMap.put("emojiCount", String.valueOf(emojiCount));

            String emojiUrl = emojiRepository.findById(id).get().getEmoji_url();
            emojisMap.put("emojisURL", emojiUrl);

            String emojiName = emojiRepository.findById(id).get().getEmoji_name();
            emojisMap.put("emojisName", emojiName);

            Boolean emojisChecked = filtered.stream().filter(e -> e.getEmoji().getId()==id).anyMatch(e -> e.getUser()==user);
            emojisMap.put("emojisChecked", emojisChecked.toString());

            emojisList.add(emojisMap);
        }
        return EmojiListResponseDto.listOf(emojisList);
    }
}
