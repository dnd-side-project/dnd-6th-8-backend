package com.travel.domain.emoji.controller;

import com.travel.domain.emoji.dto.EmojiResponse;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.emoji.service.EmojiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"이모지"})
public class EmojiApiController {
    private final EmojiService emojiService;

    @PostMapping("/archive/{archiveId}/emojiCheck")
    @ApiOperation(value = "이모지 추가 API")
    public void emojiCheck(@PathVariable long archiveId, Emoji emojiId, @ApiIgnore Principal principal) {
        emojiService.emojiCheck(archiveId, principal.getName(), emojiId);
    }

    @DeleteMapping("/archive/{archiveId}/emojiUnCheck")
    @ApiOperation(value = "이모지 취소 API")
    public void emojiUnCheck(@PathVariable long archiveId, @ApiIgnore Principal principal) {
        emojiService.emojiUnCheck(archiveId, principal.getName());
    }

}
