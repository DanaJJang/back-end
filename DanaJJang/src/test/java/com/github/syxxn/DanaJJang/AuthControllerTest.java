package com.github.syxxn.DanaJJang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syxxn.DanaJJang.entity.refreshtoken.RefreshTokenRepository;
import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.payload.request.SignInRequest;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        userRepository.save(
                User.builder()
                        .userId("testId")
                        .password(passwordEncoder.encode("testPassword"))
                        .build()
        );
    }

    @AfterEach
    public void exit() {
        userRepository.deleteAll();
        tokenRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {
        SignInRequest request = new SignInRequest("testId", "testPassword");

        mvc.perform(post("/auth")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andDo(print());
    }

}
