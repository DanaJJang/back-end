package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.response.FolderListResponse;
import com.github.syxxn.DanaJJang.payload.response.WordListResponse;
import com.github.syxxn.DanaJJang.service.folder.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;

    @GetMapping
    public FolderListResponse getFolder(Pageable page){
        return folderService.getFolder(page);
    }

    @GetMapping("/{folderId}")
    public WordListResponse getWord(@PathVariable Integer folderId){
        return folderService.getWord(folderId);
    }

    @PutMapping("/{folderId}")
    public void setName(@PathVariable Integer folderId,
                        @RequestParam String name){
        folderService.setName(folderId, name);
    }

}
