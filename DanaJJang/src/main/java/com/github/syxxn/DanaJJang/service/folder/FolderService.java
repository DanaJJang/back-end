package com.github.syxxn.DanaJJang.service.folder;

import com.github.syxxn.DanaJJang.payload.response.FolderListResponse;
import org.springframework.data.domain.Pageable;

public interface FolderService {
    FolderListResponse getFolder(Pageable page);
    void setName(Integer folderId, String name);
}
