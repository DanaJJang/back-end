package com.github.syxxn.DanaJJang;

import com.github.syxxn.DanaJJang.entity.refreshtoken.RefreshTokenRepository;
import com.github.syxxn.DanaJJang.entity.result.ResultRepository;
import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ResultControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private RefreshTokenRepository tokenRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
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

    @After
    public void deleteAll(){
        userRepository.deleteAll();
        resultRepository.deleteAll();
    }

}
