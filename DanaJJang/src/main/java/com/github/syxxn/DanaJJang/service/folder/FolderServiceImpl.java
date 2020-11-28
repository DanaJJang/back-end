package com.github.syxxn.DanaJJang.service.folder;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.entity.word.WordRepository;
import com.github.syxxn.DanaJJang.exception.FolderNotFoundException;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import com.github.syxxn.DanaJJang.payload.response.FolderListResponse;
import com.github.syxxn.DanaJJang.payload.response.FolderResponse;
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
public class FolderServiceImpl implements FolderService{

    private final FolderRepository folderRepository;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;


    @Override
    public void addFolder(String name) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        folderRepository.save(
                Folder.builder()
                        .name(name)
                        .build()
        );
    }

    @Override
    public FolderListResponse getFolder(Pageable page) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        //page 값이 없는 경우에는?

        Page<Folder> folderPage = folderRepository.findAllBy(page);

        List<FolderResponse> folderResponses = new ArrayList<>();

        for(Folder folder : folderPage) {
            folderResponses.add(
                    FolderResponse.builder()
                            .name(folder.getName())
                            .wordNumber(wordRepository.countAllById(folder.getId()))
                            .build()
            );
        }

        return FolderListResponse.builder()
                .folderResponses(folderResponses)
                .build();
    }

    @Override
    public WordListResponse getWord(Integer folderId){
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        folderRepository.findById(folderId)
                .orElseThrow(FolderNotFoundException::new);

        List<WordResponse> wordResponses = new ArrayList<>();

        for(Word word : wordRepository.findAllByFolderId(folderId)){
            wordResponses.add(
                    WordResponse.builder()
                            .english(word.getEnglish())
                            .korean(word.getKorean())
                            .build()
            );
        }

        return WordListResponse.builder()
                .wordResponses(wordResponses)
                .build();
    }

    @Override
    public void setName(Integer folderId, String name) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(FolderNotFoundException::new);

        folder.setName(name);

        folderRepository.save(folder);
    }
}
