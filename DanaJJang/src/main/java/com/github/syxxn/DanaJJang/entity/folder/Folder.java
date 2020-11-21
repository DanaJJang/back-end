package com.github.syxxn.DanaJJang.entity.folder;

import com.github.syxxn.DanaJJang.entity.word.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    public void setName(String name){
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "folder")
    private List<Word> words;

}
