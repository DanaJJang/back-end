package com.github.syxxn.DanaJJang.entity.result;

import com.github.syxxn.DanaJJang.entity.resultList.ResultList;
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
public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer resultId;

    @Column(nullable = false, length = 45)
    private String english;

    @Column(nullable = false, length = 45)
    private String korean;

    private boolean correct; //1이면 맞음, 0이면 틀림

    @ManyToOne
    @JoinColumn(name = "resultList")
    private ResultList resultList;

}
