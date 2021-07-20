package com.challenge.filestorage.controllers;

import java.io.IOException;

import com.challenge.filestorage.entities.FileStorageSingle;
import com.challenge.filestorage.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/files")
public class FileStorageController {
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping
    public FileStorageSingle saveFile(@RequestParam("file") MultipartFile file) {
        FileStorageSingle dbFile;

        try {
            dbFile = new FileStorageSingle(StringUtils.cleanPath(file.getOriginalFilename()), file.getBytes(),
                    file.getSize());
            return fileStorageService.saveFile(dbFile);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save file!");
        }
    }

    @GetMapping
    public Iterable<FileStorageSingle> getFile() {
        Iterable<FileStorageSingle> filesList = fileStorageService.getFiles();
        return filesList;
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable("id") Long id) {
        FileStorageSingle file = fileStorageService.getFileById(id);
        if (file == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");
        } else {
            fileStorageService.deleteFileById(id);
        }
    }
}