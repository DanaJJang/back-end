package com.github.syxxn.DanaJJang.entity.word;

import com.github.syxxn.DanaJJang.entity.folder.Folder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String english;

    @Column(nullable = false, length = 45)
    private String korean;

    @Column(nullable = false)
    private Integer folderId;

    @ManyToOne
    @JoinColumn(name = "folder")
    private Folder folder;

    public void modifyWord(String english, String korean){
        this.english = english;
        this.korean = korean;
    }

}
