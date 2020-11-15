package com.github.syxxn.DanaJJang.entity.folder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends CrudRepository<Folder,Integer> {
    Page<Folder> findAllBy(Pageable page);
}
