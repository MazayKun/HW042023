package ru.mikheev.kirill.hw13bank.rest.general;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.ContextAttributes;
import ru.mikheev.kirill.hw13bank.service.ATMService;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * General servlet for ATMService usage
 */
public abstract class AtmHttpServlet extends HttpServlet {

    protected ATMService atmService;
    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        atmService = (ATMService)context.getAttribute(ContextAttributes.ATM_SERVICE_NAME);
        objectMapper = (ObjectMapper)context.getAttribute(ContextAttributes.OBJECT_MAPPER_NAME);
        objectWriter = (ObjectWriter)context.getAttribute(ContextAttributes.OBJECT_WRITER_NAME);
    }

    /**
     * Covert request body from JSON to java object
     *
     * @param bodyType - body object type
     * @param request - request, which body should be converted
     * @param <O> - body object type
     * @return converting result
     */
    protected <O> O readBodyObject(Class<O> bodyType, HttpServletRequest request) throws IOException {
        String bodyContent = request.getReader().lines()
                .collect(Collectors.joining());
        return objectMapper.readValue(bodyContent, bodyType);
    }

    /**
     * Write object to response in JSON format
     *
     * @param object - Object to write
     * @param response - response object to result write to
     */
    protected void writeObjectAsJson(Object object, HttpServletResponse response) throws IOException {
        response.getWriter().println(objectWriter.writeValueAsString(object));
    }
}
