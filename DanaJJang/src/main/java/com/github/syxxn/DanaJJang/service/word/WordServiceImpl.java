package com.github.syxxn.DanaJJang.service.word;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.entity.word.WordRepository;
import com.github.syxxn.DanaJJang.exception.FolderNotFoundException;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import com.github.syxxn.DanaJJang.exception.WordAlreadyExistsException;
import com.github.syxxn.DanaJJang.exception.WordNotFoundException;
import com.github.syxxn.DanaJJang.payload.request.WordRequest;
import com.github.syxxn.DanaJJang.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService{

    private final WordRepository wordRepository;
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void addWord(WordRequest wordRequest) {

        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Folder folder = folderRepository.findById(wordRequest.getFolderId())
                .orElseThrow(FolderNotFoundException::new);

        wordRepository.findByEnglishAndKorean(wordRequest.getEnglish(),wordRequest.getKorean())
                .ifPresent(w->{ throw new WordAlreadyExistsException(); });

        wordRepository.save(
                Word.builder()
                        .folderId(folder.getId())
                        .english(wordRequest.getEnglish())
                        .korean(wordRequest.getKorean())
                .build()
        );
    }

    @Override
    public void modifyWord(Integer wordId, String english, String korean) {

        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Word word = wordRepository.findById(wordId)
                .orElseThrow(WordNotFoundException::new);

        word.modifyWord(english, korean);
    }

    @Override
    public void deleteWord(Integer wordId) {

        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Word word = wordRepository.findById(wordId)
                .orElseThrow(WordNotFoundException::new);

        wordRepository.delete(word);
    }
}
