package ru.mikheev.kirill.hw16patterns.composite.component;

public class File implements FileSystemMember {

    private String name;
    private String extension;

    public File(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    @Override
    public void copy(FileSystemMember dest) {
        File copy = new File(name, extension);
        dest.paste(copy);
    }

    @Override
    public void paste(FileSystemMember newMember) {
        throw new RuntimeException("There is no way to paste file system member to file");
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public String getInfo() {
        return name + '.' + extension;
    }
}
