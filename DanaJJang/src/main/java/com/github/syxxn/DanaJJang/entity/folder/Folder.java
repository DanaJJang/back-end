package com.github.syxxn.DanaJJang.entity.folder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Table(name="Folder")
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, length = 30)
    private String name;

    public void setName(String name){
        this.name=name;
    }

}
