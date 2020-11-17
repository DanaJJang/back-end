package com.github.syxxn.DanaJJang.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WordResponse {

    private String english;

    private String korean;

}
