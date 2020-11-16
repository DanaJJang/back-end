package com.github.syxxn.DanaJJang.service.folder;

import com.github.syxxn.DanaJJang.payload.response.FolderListResponse;
import com.github.syxxn.DanaJJang.payload.response.WordListResponse;
import org.springframework.data.domain.Pageable;

public interface FolderService {
    FolderListResponse getFolder(Pageable page);
    WordListResponse getWord(Integer folderId);
    void setName(Integer folderId, String name);
}
