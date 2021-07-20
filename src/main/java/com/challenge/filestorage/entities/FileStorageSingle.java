package com.challenge.filestorage.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileStorageSingle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private Long fileSize;

    @Lob
    private byte[] data;

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public FileStorageSingle() {
    }

    public FileStorageSingle(String fileName, byte[] data, Long fileSize) {
        this.fileName = fileName;
        this.data = data;
        this.fileSize = fileSize;
    }
}