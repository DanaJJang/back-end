package com.github.syxxn.DanaJJang.entity.word;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<Word,Integer> {
    Page<Word> findAllByFolderId(Integer id, Pageable page);
    //단어 중복확인
    Optional<Word> findByEnglishAndKorean(String english, String korean);
    //단어 개수
    Integer countAllById(Integer folderId);
}

/*

 */