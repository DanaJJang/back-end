package com.github.syxxn.DanaJJang.entity.result;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result,Integer> {
    List<Result> findAllByResultId(Integer resultId);
}
