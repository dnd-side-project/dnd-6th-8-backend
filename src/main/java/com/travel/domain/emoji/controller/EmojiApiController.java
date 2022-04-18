package com.travel.domain.emoji.controller;

import com.travel.domain.emoji.service.EmojiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"이모지"})
public class EmojiApiController {
    private final EmojiService emojiService;

    @PostMapping("/archive/{ARCHIVE_ID}/{EMOJI_ID}/emojiCheck")
    @ApiOperation(value = "이모지 추가 API")
    public void emojiCheck(@PathVariable long ARCHIVE_ID, @PathVariable long EMOJI_ID, @ApiIgnore Principal principal) {
        emojiService.emojiCheck(ARCHIVE_ID, principal.getName(), EMOJI_ID);
    }

    @DeleteMapping("/archive/{USER_EMOJI_SELECTED_ID}/emojiUnCheck")
    @ApiOperation(value = "이모지 취소 API")
    public void emojiUnCheck(@PathVariable Long USER_EMOJI_SELECTED_ID) {
        emojiService.emojiUnCheck(USER_EMOJI_SELECTED_ID);
    }

}
