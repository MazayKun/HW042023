package ru.mikheev.kirill.hw33publisher.publishersubscriber.listeners;

import ru.mikheev.kirill.hw33publisher.publishersubscriber.EventListener;
import ru.mikheev.kirill.hw33publisher.publishersubscriber.events.AddEvent;

import java.io.*;

/**
 * ��������� ������� � ���������� ��������� � ��������� � ����
 */
public class AddFileListener implements EventListener<AddEvent> {

    /**
     * ����, � ������� ����� ���������� �������
     */
    private final File outputFile;

    public AddFileListener(String outputFileName) {
        this.outputFile = new File(outputFileName);
        outputFile.delete();
    }

    @Override
    public void notify(AddEvent event) {
        try(Writer fileWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            fileWriter.write(event.toString());
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
