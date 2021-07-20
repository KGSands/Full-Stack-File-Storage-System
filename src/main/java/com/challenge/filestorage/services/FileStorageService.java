package com.challenge.filestorage.services;

import com.challenge.filestorage.entities.FileStorageSingle;
import com.challenge.filestorage.repositories.FileStorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {

    @Autowired
    FileStorageRepository fileStorageRepository;

    public FileStorageSingle saveFile(FileStorageSingle file) {
        return fileStorageRepository.save(file);
    }

    public Iterable<FileStorageSingle> getFiles() {
        return fileStorageRepository.findAll();
    }

    public FileStorageSingle getFileById(Long id) {
        return fileStorageRepository.findById(id).orElse(null);
    }

    public void deleteFileById(Long id) {
        fileStorageRepository.deleteById(id);
    }
}