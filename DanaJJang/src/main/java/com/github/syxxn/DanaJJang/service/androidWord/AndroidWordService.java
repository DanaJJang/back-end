package com.github.syxxn.DanaJJang.service.androidWord;

import com.github.syxxn.DanaJJang.payload.response.WordListResponse;
import org.springframework.data.domain.Pageable;

public interface AndroidWordService {
    WordListResponse getWord(Pageable page);
    void addWord(String english, String korean);
    void modifyWord(Integer wordId, String english, String korean);
    void deleteWord(Integer wordId);
}
