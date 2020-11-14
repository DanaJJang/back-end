package com.github.syxxn.DanaJJang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syxxn.DanaJJang.entity.folder.Folder;
import com.github.syxxn.DanaJJang.entity.folder.FolderRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void deleteAll(){
        folderRepository.deleteAll();;
    }

    @Test
    public void getFolder() throws  Exception{

    }

    @Test
    public void setName() throws Exception{

        int folderId = createFolder("기말고사");

        mvc.perform(patch("/team/"+folderId)
                .param("중간고사")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print());

    }

    public Integer createFolder(String name){
        return folderRepository.save(
                Folder.builder()
                        .name(name)
                .build()
        ).getId();
    }

}
