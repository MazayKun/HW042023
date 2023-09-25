package ru.mikheev.kirill.hw34customwebserver.processing;

import ru.mikheev.kirill.hw34customwebserver.exception.PageNotFoundException;
import ru.mikheev.kirill.hw34customwebserver.exception.WrongUriMappingFormatException;
import ru.mikheev.kirill.hw34customwebserver.property.ServerProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * Process request content and resolve name of page, which requested user
 */
public class RequestProcessor {

    private final Map<String, String> pagesMapping;

    public RequestProcessor(ServerProperties serverProperties) {
        pagesMapping = new HashMap<>();
        for (String solidMapping : serverProperties.getUri()) {
            String[] mappingAsArray = solidMapping.split(":");
            if (mappingAsArray.length != 2) throw new WrongUriMappingFormatException(mappingAsArray);
            pagesMapping.put(mappingAsArray[0], mappingAsArray[1]);
        }
    }

    /**
     * Process request content and resolve name of page, which requested user
     *
     * @param request - reader of request content
     * @throws PageNotFoundException if there is no mapping for requested uri
     */
    public String getPageByRequest(BufferedReader request) throws IOException {
        String[] firstLineComponents = request.readLine().split(" ");
        if (!"GET".equals(firstLineComponents[0])) throw new RuntimeException("Bad request method");
        if (!"HTTP/1.1".equals(firstLineComponents[2])) throw new RuntimeException("Bad protocol name");
        String pageName = pagesMapping.get(firstLineComponents[1]);
        if (isNull(pageName)) {
            throw new PageNotFoundException();
        }
        return pageName;
    }
}
