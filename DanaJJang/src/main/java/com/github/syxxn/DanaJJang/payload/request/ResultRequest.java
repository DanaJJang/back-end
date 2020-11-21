package com.github.syxxn.DanaJJang.payload.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultRequest {

    private Integer resultListId;

    private String english;

    private String korean;

    private Boolean correct;

}
