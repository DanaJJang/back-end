package com.github.syxxn.DanaJJang.entity.and_word;

import com.github.syxxn.DanaJJang.entity.word.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AndroidWordRepository extends CrudRepository<AndroidWord,Integer> {
    Optional<Word> findByEnglishAndKorean(String english, String korean);
    Page<AndroidWord> findAllBy(Pageable page);
}
