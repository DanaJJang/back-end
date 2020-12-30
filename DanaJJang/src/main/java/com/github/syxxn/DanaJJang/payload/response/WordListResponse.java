package com.github.syxxn.DanaJJang.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WordListResponse {

    private int totalElements;

    private int totalPages;

    private List<WordResponse> wordResponses;

}
