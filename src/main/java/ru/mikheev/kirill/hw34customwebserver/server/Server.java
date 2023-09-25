package ru.mikheev.kirill.hw34customwebserver.server;

import ru.mikheev.kirill.hw34customwebserver.exception.PageNotFoundException;
import ru.mikheev.kirill.hw34customwebserver.processing.PageResolver;
import ru.mikheev.kirill.hw34customwebserver.processing.RequestProcessor;
import ru.mikheev.kirill.hw34customwebserver.processing.Response;
import ru.mikheev.kirill.hw34customwebserver.property.ServerProperties;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.mikheev.kirill.hw34customwebserver.util.ResponseCode.*;

/**
 * Central entity of application
 * Accept connections and submit it to processing
 */
public class Server {

    private final ServerProperties serverProperties;
    private final RequestProcessor requestProcessor;
    private final ExecutorService connectionHandlerPool;
    private final PageResolver pageResolver = new PageResolver();

    private ServerSocket serverSocket;

    public Server(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
        this.requestProcessor = new RequestProcessor(serverProperties);
        this.connectionHandlerPool = Executors.newWorkStealingPool(serverProperties.getConfig().getConcurrency());
    }

    public void startServer() {
        if(serverSocket != null) {
            throw new RuntimeException("Server already started");
        }
        try {
            serverSocket = new ServerSocket(serverProperties.getConfig().getPort());
        } catch (IOException e) {
            throw new RuntimeException("Error during socket creation", e);
        }
        new Thread(this::listenPort)
                .start();
        System.out.println("Server started");
    }

    private void listenPort() {
        try {
            while(true) {
                Socket newConnection = serverSocket.accept();
                connectionHandlerPool.execute(() -> handleConnection(newConnection));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("Error during accepting new connection", e);
        }
    }

    private void handleConnection(Socket newConnection) {
        try (
                BufferedReader requestReader = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
                PrintWriter responseWriter = new PrintWriter(new OutputStreamWriter(newConnection.getOutputStream()))
        ) {
            handleConnection(requestReader, responseWriter);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error with socket", e);
        }
    }

    private void handleConnection(BufferedReader requestReader, PrintWriter responseWriter) {
        Response response;
        try {
            String pagePath = requestProcessor.getPageByRequest(requestReader);
            response = Response.ok();
            response.setPageAsResponseBody(pageResolver.getPageContentByPath(pagePath));
        } catch (PageNotFoundException e) {
            response = Response.notFound();
            response.setPageAsResponseBody(pageResolver.getErrorPage(NOT_FOUND_CODE + ' ' + NOT_FOUND_MESSAGE));
        } catch (IOException e) {
            response = Response.internalServerError();
            response.setPageAsResponseBody(pageResolver.getErrorPage(INTERNAL_SERVER_ERROR_CODE + ' ' + INTERNAL_SERVER_ERROR_MESSAGE));
        }

        responseWriter.write(response.toString());
    }
}
