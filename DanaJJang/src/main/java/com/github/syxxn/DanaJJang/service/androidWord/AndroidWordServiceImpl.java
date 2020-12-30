package com.github.syxxn.DanaJJang.service.androidWord;

import com.github.syxxn.DanaJJang.entity.and_word.AndroidWord;
import com.github.syxxn.DanaJJang.entity.and_word.AndroidWordRepository;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import com.github.syxxn.DanaJJang.exception.WordAlreadyExistsException;
import com.github.syxxn.DanaJJang.exception.WordNotFoundException;
import com.github.syxxn.DanaJJang.payload.response.WordListResponse;
import com.github.syxxn.DanaJJang.payload.response.WordResponse;
import com.github.syxxn.DanaJJang.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AndroidWordServiceImpl implements AndroidWordService{

    private final AndroidWordRepository androidWordRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    private static void accept(Word w) {
        throw new WordAlreadyExistsException();
    }


    @Override
    public WordListResponse getWord(Pageable page) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Page<AndroidWord> wordPage = androidWordRepository.findAllBy(page);

        List<WordResponse> wordResponses = new ArrayList<>();

        for(AndroidWord word : wordPage){
            wordResponses.add(
                    WordResponse.builder()
                            .english(word.getEnglish())
                            .korean(word.getKorean())
                            .build()
            );
        }

        return WordListResponse.builder()
                .totalElements(wordPage.getNumberOfElements())
                .totalPages(wordPage.getTotalPages())
                .wordResponses(wordResponses)
                .build();
    }

    @Override
    public void addWord(String english, String korean) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        androidWordRepository.findByEnglishAndKorean(english,korean)
                .ifPresent(AndroidWordServiceImpl::accept);

        androidWordRepository.save(
                AndroidWord.builder()
                        .english(english)
                        .korean(korean)
                        .build()
        );
    }

    @Override
    public void modifyWord(Integer wordId, String english, String korean) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        AndroidWord word = androidWordRepository.findById(wordId)
                .orElseThrow(WordNotFoundException::new);

        word.modifyWord(english, korean);
    }

    @Override
    public void deleteWord(Integer wordId) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        AndroidWord word = androidWordRepository.findById(wordId)
                .orElseThrow(WordNotFoundException::new);

        androidWordRepository.delete(word);
    }

}
