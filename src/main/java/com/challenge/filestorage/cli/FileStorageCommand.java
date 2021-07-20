package com.challenge.filestorage.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import com.challenge.filestorage.entities.FileStorageSingle;
import com.challenge.filestorage.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

@ShellComponent
public class FileStorageCommand {
    @Autowired
    FileStorageService fileStorageService;

    @ShellMethod("Display a list of files [list-files]")
    public Table listFiles() {
        Iterable<FileStorageSingle> files = fileStorageService.getFiles();

        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("fileName", "File Name");
        headers.put("fileSize", "File Size (bytes)");
        TableModel model = new BeanListTableModel<>(files, headers);

        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        return tableBuilder.build();
    }

    @ShellMethod("Upload a file [upload-file <full filepath>] e.g. upload-file C:/Users/Keelan/Desktop/KeelanCV.pdf")
    public void uploadFile(String filePath) {
        Path path = Paths.get(filePath);
        byte[] fileData;
        try {
            fileData = Files.readAllBytes(path);
            Integer fileSize = fileData.length;
            if (fileSize > 10485760) {
                System.out.println("The file exceeds its maximum permitted size of 10485760 bytes. (10MB)");
            } else {
                FileStorageSingle dbFile = new FileStorageSingle(path.getFileName().toString(), fileData,
                        Long.valueOf(fileSize));
                fileStorageService.saveFile(dbFile);
            }
        } catch (Exception e) {
            System.out.println("Could not save the file. Please check the filepath and try again.");
        }
    }

    @ShellMethod("Delete a file using the file's ID [delete-file <id>] e.g. delete-file 33")
    public void deleteFile(Long id) {
        FileStorageSingle file = fileStorageService.getFileById(id);
        if (file == null) {
            System.out.println("Incorrect file ID - file not found");
        } else {
            fileStorageService.deleteFileById(id);
            System.out.println("File successfully deleted");
        }
    }

}