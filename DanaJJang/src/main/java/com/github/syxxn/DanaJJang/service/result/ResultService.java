package com.github.syxxn.DanaJJang.service.result;

import com.github.syxxn.DanaJJang.payload.request.ResultRequest;
import com.github.syxxn.DanaJJang.payload.response.ResultListResponse;

public interface ResultService {
    void addResult(ResultRequest[] resultRequest);
    ResultListResponse getResult(Integer resultId);
}
