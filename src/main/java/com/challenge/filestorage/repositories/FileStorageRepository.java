package com.challenge.filestorage.repositories;

import com.challenge.filestorage.entities.FileStorageSingle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileStorageRepository extends PagingAndSortingRepository<FileStorageSingle, Long> {
    @Override
    Page<FileStorageSingle> findAll(Pageable pageable);

}
