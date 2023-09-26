package ru.mikheev.kirill.hw34customwebserver.processing;

import lombok.AccessLevel;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;
import static ru.mikheev.kirill.hw34customwebserver.util.ResponseCode.*;

/**
 * Result of request processing
 */
public class Response {

    private static final String DEFAULT_PROTOCOL = "HTTP/1.1";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    private static final String TEXT_HTML_CONTENT_TYPE = "text/html; charset=UTF-8";

    private final String protocolName;
    private final Map<String, String> headers = new HashMap<>();
    @Setter(AccessLevel.PRIVATE)
    private int responseCode;
    @Setter(AccessLevel.PRIVATE)
    private String responseMessage;
    private String body;

    private Response() {
        protocolName = DEFAULT_PROTOCOL;
    }

    /**
     * @return Response with OK status
     */
    public static Response ok() {
        Response response = new Response();
        response.setResponseCode(OK_CODE);
        response.setResponseMessage(OK_MESSAGE);
        return response;
    }

    /**
     * @return Response with NOT_FOUND status
     */
    public static Response notFound() {
        Response response = new Response();
        response.setResponseCode(NOT_FOUND_CODE);
        response.setResponseMessage(NOT_FOUND_MESSAGE);
        return response;
    }

    /**
     * @return Response with INTERNAL_SERVER_ERROR status
     */
    public static Response internalServerError() {
        Response response = new Response();
        response.setResponseCode(INTERNAL_SERVER_ERROR_CODE);
        response.setResponseMessage(INTERNAL_SERVER_ERROR_MESSAGE);
        return response;
    }

    public void addCustomHeader(String headerName, String headerValue) {
        headers.put(headerName, headerValue);
    }

    public void setContentType(String contentType) {
        headers.put(CONTENT_TYPE_HEADER, contentType);
    }

    public void setContentLength(int contentLength) {
        headers.put(CONTENT_LENGTH_HEADER, Integer.toString(contentLength));
    }

    public void setPageAsResponseBody(String pageContent) {
        body = pageContent;
        setContentType(TEXT_HTML_CONTENT_TYPE);
        setContentLength(pageContent.length());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(protocolName).append(' ').append(responseCode).append(' ').append(responseMessage);
        sb.append('\n');
        headers.forEach((name, value) -> sb.append(name).append(": ").append(value).append('\n'));
        if (nonNull(body)) {
            sb.append('\n');
            sb.append(body);
        }
        return sb.toString();
    }
}
