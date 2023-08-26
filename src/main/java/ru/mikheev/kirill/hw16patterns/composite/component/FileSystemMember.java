package ru.mikheev.kirill.hw16patterns.composite.component;

public interface FileSystemMember {

    void copy(FileSystemMember dest);

    void paste(FileSystemMember newMember);

    boolean isDirectory();

    String getInfo();
}
