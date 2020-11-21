package com.github.syxxn.DanaJJang.service.result;

import com.github.syxxn.DanaJJang.entity.result.Result;
import com.github.syxxn.DanaJJang.entity.result.ResultRepository;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.exception.ResultNotFoundException;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import com.github.syxxn.DanaJJang.payload.request.ResultRequest;
import com.github.syxxn.DanaJJang.payload.response.ResultListResponse;
import com.github.syxxn.DanaJJang.payload.response.ResultResponse;
import com.github.syxxn.DanaJJang.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void addResult(ResultRequest[] resultRequest) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        List<ResultRequest> resultRequests = new ArrayList<>();


        //요청된 크기만큼 돌려서 저장하기
        /*resultRepository.save(
                Result.builder()
                        .resultList(resultRequest.)
                        .korean(resultRequest.)
                        .english(resultRequest.)
                .build()
        );*/

    }

    @Override
    public ResultListResponse getResult(Integer resultId) {
        userRepository.findByUserId(authenticationFacade.getUserId())
                .orElseThrow(UserNotFoundException::new);

        resultRepository.findById(resultId)
                .orElseThrow(ResultNotFoundException::new);

        List<ResultResponse> resultResponses = new ArrayList<>();

        for(Result result : resultRepository.findAllByResultId(resultId)){
            resultResponses.add(
                    ResultResponse.builder()
                            .english(result.getEnglish())
                            .korean(result.getKorean())
                            .build()
            );
        }

        return ResultListResponse.builder()
                .resultResponses(resultResponses)
                .build();

    }
}
