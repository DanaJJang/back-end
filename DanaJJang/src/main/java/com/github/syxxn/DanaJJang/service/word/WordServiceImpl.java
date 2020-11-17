package com.github.syxxn.DanaJJang.service.word;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.entity.word.WordRepository;
import com.github.syxxn.DanaJJang.exception.FolderNotFoundException;
import com.github.syxxn.DanaJJang.exception.WordAlreadyExistsException;
import com.github.syxxn.DanaJJang.exception.WordNotFoundException;
import com.github.syxxn.DanaJJang.payload.request.WordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService{

    private final WordRepository wordRepository;
    private final FolderRepository folderRepository;

    @Override
    public void addWord(WordRequest wordRequest) {

        Folder folder = folderRepository.findById(wordRequest.getFolderId())
                .orElseThrow(FolderNotFoundException::new);

        Word word = wordRepository.findByEnglish(wordRequest.getEnglish())
                .orElseThrow(WordAlreadyExistsException::new);

        wordRepository.save(word.word());

        wordRepository.save(
                Word.builder()
                        .folderId(folder.getId())
                        .english(wordRequest.getEnglish())
                        .korean(wordRequest.getKorean())
                        .number(word.getNumber())
                .build()
        );

    }

    @Override
    public void modifyWord(Integer wordId, String english, String korean) {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(WordNotFoundException::new);

        word.modifyWord(english, korean);
    }

    @Override
    public void deleteWord(Integer wordId) {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(WordNotFoundException::new);

        wordRepository.delete(word);
    }
}
