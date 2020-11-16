package com.github.syxxn.DanaJJang;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
import com.github.syxxn.DanaJJang.entity.word.Word;
import com.github.syxxn.DanaJJang.entity.word.WordRepository;
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
public class FolderControllerTest {

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
        folderRepository.save(
                Folder.builder()
                        .id(2)
                        .name("second")
                        .build()
        );
    }

    @After
    public void deleteAll(){
        folderRepository.deleteAll();;
    }

    @Test
    public void getFolder() throws  Exception {
        mvc.perform(get("/folder")).andDo(print())
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getWord() throws Exception {
        addWord(1);
        mvc.perform(get("/folder/1")).andDo(print())
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void setName() throws Exception {
        int folderId = createFolder(1);

        mvc.perform(put("/folder/"+folderId)
                .param("name","중간고사")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print());
    }

    private Integer createFolder(Integer id) {
        return folderRepository.save(
                Folder.builder()
                        .id(id)
                        .name("first")
                        .build()
        ).getId();
    }

    private Integer addWord(Integer folderId){
        return wordRepository.save(
                Word.builder()
                        .folderId(folderId)
                        .english("hello")
                        .korean("hello")
                        .build()
        ).getId();
    }

}
