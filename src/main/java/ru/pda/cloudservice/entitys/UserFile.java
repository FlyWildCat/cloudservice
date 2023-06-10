package ru.pda.cloudservice.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String fileName;
    private byte[] fileContent;
    private int fileSize;

    public UserFile() {
    }

    public UserFile(Long uid, String fileName, byte[] fileContent) {
        this.uid = uid;
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileSize = fileContent.length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public int getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "{" + "\"filename\": \"" + fileName + "\", \"size\": " + fileSize + '}';
    }
}
