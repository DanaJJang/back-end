package com.github.syxxn.DanaJJang.entity.word;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<Word,Integer> {
    List<Word> findAllByFolderId(Integer id);
    //단어 중복확인
    Optional<Word> findByEnglish(String english);
}
