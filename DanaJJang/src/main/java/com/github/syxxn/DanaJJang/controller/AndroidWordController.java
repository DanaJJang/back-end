package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.response.WordListResponse;
import com.github.syxxn.DanaJJang.service.androidWord.AndroidWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/android/word")
public class AndroidWordController {

    private final AndroidWordService androidWordService;

    @GetMapping
    public WordListResponse getWord(Pageable page){
        return androidWordService.getWord(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addWord(@RequestBody String english,
                        @RequestBody String korean){
        androidWordService.addWord(english, korean);
    }

    @PutMapping("/{wordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyWord(@PathVariable Integer wordId,
                           @RequestParam String english,
                           @RequestParam String korean){
        androidWordService.modifyWord(wordId, english, korean);
    }

    @DeleteMapping("/{wordId}")
    public void deleteWord(@PathVariable Integer wordId){
        androidWordService.deleteWord(wordId);
    }

}
