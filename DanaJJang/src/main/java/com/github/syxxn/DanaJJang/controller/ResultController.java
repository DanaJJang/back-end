package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.request.ResultRequest;
import com.github.syxxn.DanaJJang.payload.response.ResultListResponse;
import com.github.syxxn.DanaJJang.service.result.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public void addResult(
            @RequestBody ResultRequest[] resultRequest){
        resultService.addResult(resultRequest);
    }

    @GetMapping("/{resultId}")
    public ResultListResponse getResult(@PathVariable Integer resultId){
        return resultService.getResult(resultId);
    }
}
