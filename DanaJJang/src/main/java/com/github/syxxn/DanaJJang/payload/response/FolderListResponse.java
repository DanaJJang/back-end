package com.github.syxxn.DanaJJang.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FolderListResponse {

    private List folderResponses;

    private Integer wordNumber;

}
