package com.github.syxxn.DanaJJang.service.word;

import com.github.syxxn.DanaJJang.payload.request.WordRequest;

public interface WordService {
    void addWord(WordRequest wordRequest);
    void modifyWord(Integer wordId, String english, String korean);
    void deleteWord(Integer wordId);
}
