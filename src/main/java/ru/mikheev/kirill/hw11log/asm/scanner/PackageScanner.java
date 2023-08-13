package ru.mikheev.kirill.hw11log.asm.scanner;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class PackageScanner {

    private static final String BUILD_DIRECTORY_PATH = "build/classes/java/main/";
    private static final String PACKAGE_FORMAT_BUILD_DIRECTORY_PATH = BUILD_DIRECTORY_PATH.replace('/', '.');

    @SuppressWarnings("ConstantConditions")
    public static void scanForLogAnnotation(Package rootPackage) {
        File rootDirectory = new File(BUILD_DIRECTORY_PATH + rootPackage.getName().replace('.', '/'));
        LinkedList<File> notProcessedFiles = new LinkedList<>();
        Collections.addAll(notProcessedFiles, rootDirectory.listFiles());
        while ( ! notProcessedFiles.isEmpty()) {
            File currFile = notProcessedFiles.pop();
            if (currFile.isDirectory()) {
                Collections.addAll(notProcessedFiles, currFile.listFiles());
            } else {
                processSingleClass(currFile);
            }
        }
    }

    private static void processSingleClass(File classRepresentation) {
        try (InputStream fis = new FileInputStream(classRepresentation)) {
            ClassReader classReader = new ClassReader(fis.readAllBytes());
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classReader.accept(new LogAnnotationClassScanner(classWriter), ClassReader.EXPAND_FRAMES);

            try (OutputStream fos = new FileOutputStream(classRepresentation)) {
                fos.write(classWriter.toByteArray());
            }
            Class<?> currClass = Class.forName(classRepresentation.getPath()
                    .replace('\\', '.')
                    .replace(PACKAGE_FORMAT_BUILD_DIRECTORY_PATH, "")
                    .replace(".class", ""));
            currClass.getClassLoader().loadClass(currClass.getName());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Exception when trying to add output of information about input parameters of class's methods", e);
        }
    }
}
