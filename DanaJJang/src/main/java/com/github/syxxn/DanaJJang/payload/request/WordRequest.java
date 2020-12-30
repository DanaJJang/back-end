package com.github.syxxn.DanaJJang.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WordRequest {

    private Integer folderId;

    private String english;

    private String korean;

}
