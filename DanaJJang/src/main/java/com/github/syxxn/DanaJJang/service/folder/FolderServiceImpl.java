package com.github.syxxn.DanaJJang.service.folder;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.exception.FolderNotFoundException;
import com.github.syxxn.DanaJJang.payload.response.FolderListResponse;
import com.github.syxxn.DanaJJang.payload.response.FolderResponse;
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

    @Override
    public FolderListResponse getFolder(Pageable page) {
        Page<Folder> folderPage = folderRepository.findAllBy(page);

        List<FolderResponse> folderResponses = new ArrayList<>();

        for(Folder folder : folderPage) {
            folderResponses.add(
                    FolderResponse.builder()
                            .name(folder.getName())
                            .totalWords()
                            .build()
            );
        }

        return FolderListResponse.builder()
                .totalElements((int)folderPage.getTotalElements())
                .folderResponses(folderResponses)
                .build();
    }

    @Override
    public void setName(Integer folderId, String name) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(FolderNotFoundException::new);

        folder.setName(name);

        folderRepository.save(folder);
    }
}
