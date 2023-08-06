package ru.mikheev.kirill.hw16patterns.composite.component;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemMember {

    private String name;
    private List<FileSystemMember> members;

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public void copy(FileSystemMember dest) {
        Directory copy = new Directory(name);
        if(members != null) {
            for(FileSystemMember member : members) {
                member.copy(copy);
            }
        }
        dest.paste(copy);
    }

    @Override
    public void paste(FileSystemMember newMember) {
        if(members == null) {
            members = new ArrayList<>();
        }
        members.add(newMember);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder(name + " : {");
        if(members != null) {
            for(FileSystemMember member : members) {
                sb.append(member.getInfo())
                        .append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append('}');
        return sb.toString();
    }
}
