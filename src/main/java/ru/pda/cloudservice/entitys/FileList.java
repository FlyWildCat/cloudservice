package ru.pda.cloudservice.entitys;

public class FileList {
    private String filename;
    private int size;

    public FileList(String filename, int size) {
        this.filename = filename;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
