package com.github.syxxn.DanaJJang.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultResponse {

    private Integer resultListId;

    private String english;

    private String korean;

    private Boolean correct;

}