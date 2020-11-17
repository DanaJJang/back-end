package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.request.WordRequest;
import com.github.syxxn.DanaJJang.service.word.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;

    @PostMapping
    public void addWord(@RequestBody WordRequest wordRequest){
        wordService.addWord(wordRequest);
    }

    @PutMapping("/{wordId}")
    public void modifyWord(@PathVariable Integer wordId,
                           @RequestParam String english,
                           @RequestParam String korean){
        wordService.modifyWord(wordId, english, korean);
    }

    @DeleteMapping("/{wordId}")
    public void deleteWord(@PathVariable Integer wordId){
        wordService.deleteWord(wordId);
    }
}
