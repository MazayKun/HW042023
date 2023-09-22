package ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.EventListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.RemoveEvent;

import java.io.*;

/**
 * ���������� � ���� ������� �������� ��������� �� ���������
 */
public class RemoveFileListener implements EventListener<RemoveEvent> {

    /**
     * ����, � ������� ����� ���������� �������
     */
    private final File outputFile;

    public RemoveFileListener(String outputFileName) {
        this.outputFile = new File(outputFileName);
        outputFile.delete();
    }

    @Override
    public void notify(RemoveEvent event) {
        try (Writer fileWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            fileWriter.write(event.toString());
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
