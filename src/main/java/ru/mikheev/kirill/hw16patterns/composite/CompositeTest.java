package ru.mikheev.kirill.hw16patterns.composite;

import ru.mikheev.kirill.hw16patterns.composite.component.Directory;
import ru.mikheev.kirill.hw16patterns.composite.component.File;

public class CompositeTest {

    public static void main(String[] args) {
        Directory docsDirectory = new Directory("Docs");
        Directory projectsDirectory = new Directory("Projects");
        projectsDirectory.paste(
                new File("HelloWorld", "cpp")
        );
        projectsDirectory.paste(
                new File("HelloWorld", "java")
        );
        projectsDirectory.paste(
                new File("HelloWorld", "py")
        );
        Directory root = new Directory("usr");
        root.paste(docsDirectory);
        root.paste(projectsDirectory);
        System.out.println(root.getInfo());
        projectsDirectory.copy(docsDirectory);
        System.out.println(root.getInfo());
    }
}
