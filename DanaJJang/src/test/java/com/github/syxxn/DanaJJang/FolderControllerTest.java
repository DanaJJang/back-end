package com.github.syxxn.DanaJJang;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.entity.word.WordRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FolderControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mvc;

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
        folderRepository.save(
                Folder.builder()
                        .id(1)
                        .name("first")
                        .build()
        );
        folderRepository.save(
                Folder.builder()
                        .id(2)
                        .name("second")
                        .build()
        );
    }

    @After
    public void deleteAll(){
        userRepository.deleteAll();
        folderRepository.deleteAll();;
        wordRepository.deleteAll();;
    }

    @Test
    @WithMockUser(username = "testId",password = "testPassword")
    public void getFolder() throws  Exception {
        mvc.perform(get("/folder")).andDo(print())
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "testId",password = "testPassword")
    public void getWord() throws Exception {
        Folder folder = createFolder();
        addWord(folder);
        mvc.perform(get("/folder/" + folder.getId())).andDo(print())
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "testId",password = "testPassword")
    public void setName() throws Exception {
        Folder folder = createFolder();

        mvc.perform(put("/folder/"+folder.getId())
                .param("name","중간고사")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print());
    }

    private Folder createFolder() {
        return folderRepository.save(
                Folder.builder()
                        .name("first")
                        .build()
        );
    }

    private Integer addWord(Folder folder){
        return wordRepository.save(
                Word.builder()
                        .folder(folder)
                        .english("hello")
                        .korean("hello")
                        .build()
        ).getId();
    }

}
