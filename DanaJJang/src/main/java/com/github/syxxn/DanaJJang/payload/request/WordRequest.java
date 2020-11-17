package com.github.syxxn.DanaJJang.payload.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WordRequest {

    private Integer folderId;

    private String english;

    private String korean;

}
