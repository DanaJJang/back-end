package com.github.syxxn.DanaJJang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.entity.word.WordRepository;
import com.github.syxxn.DanaJJang.payload.request.WordRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class WordControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private WordRepository wordRepository;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        folderRepository.save(
                Folder.builder()
                        .id(1)
                        .name("first")
                        .build()
        );
    }

    @After
    public void deleteAll(){
        folderRepository.deleteAll();;
        wordRepository.deleteAll();
    }

    @Test
    public void addWord() throws Exception {
        WordRequest request = WordRequest.builder()
                .folderId(addFolder())
                .english("haha")
                .korean("haha")
                .build();

        mvc.perform(post("/word")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void modifyWord() throws Exception{
        Integer wordId = addWord(1);

        mvc.perform(put("/word/"+wordId)
                .param("english","hi")
                .param("korean","hi")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void deleteWord() throws Exception{
        Integer wordId = addWord(1);

        mvc.perform(delete("/word/"+wordId))
                .andExpect(status().isOk()).andDo(print());
    }

    private Integer addFolder() {
        return folderRepository.save(
                Folder.builder()
                        .name("first")
                        .build()
        ).getId();
    }

    private Integer addWord(Integer folderId) {
        return wordRepository.save(
                Word.builder()
                        .folderId(folderId)
                        .english("hello")
                        .korean("hello")
                        .build()
        ).getId();
    }

}
