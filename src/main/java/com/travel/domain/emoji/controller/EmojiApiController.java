package com.travel.domain.emoji.controller;

import com.travel.domain.emoji.dto.EmojiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"이모지"})
public class EmojiApiController {

    @PostMapping("/archive/{archiveId}")
    @ApiOperation(value = "이모지 추가 API")
    public ResponseEntity<Void> addEmoji(@RequestParam String emojiName){
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/archive/{archiveId}")
    @ApiOperation(value = "이모지 취소 API")
    public ResponseEntity<List<EmojiResponse>> getAllEmojiUserGot(){
        List EmojiResponse = new ArrayList();
        return ResponseEntity.noContent().build();
    }

}
