package com.github.syxxn.DanaJJang.entity.and_word;

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
public class AndroidWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String english;

    @Column(nullable = false, length = 45)
    private String korean;

    public void modifyWord(String english, String korean){
        this.english = english;
        this.korean = korean;
    }

}
