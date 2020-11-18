package com.github.syxxn.DanaJJang.service.auth;

import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import com.github.syxxn.DanaJJang.payload.request.SignInRequest;
import com.github.syxxn.DanaJJang.payload.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

   public final UserRepository userRepository;

   @Override
    public TokenResponse signIn(SignInRequest signInRequest){
       String userId=signInRequest.getUserId();
       String password=signInRequest.getPassword();

       User user=userRepository.findByUserId(userId)
               .orElseThrow(UserNotFoundException::new);

       if(!user.getPassword().equals(password))throw new UserNotFoundException();
       return tokenResponse(user.getId());
   }
   private TokenResponse tokenResponse(Integer id){
       return new TokenResponse(id);
   }
}
