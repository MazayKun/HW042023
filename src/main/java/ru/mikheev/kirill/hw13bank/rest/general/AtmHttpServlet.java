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
 * ����� ����� ��������� ��� ������ � ATMService
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
     * ������������ ���� ������� � ������� JSON � ������
     *
     * @param bodyType - ��� ������� ���� �������
     * @param request - ������, ���� �������� ����� ��������������
     * @param <O> - ��� ������� ���� ������
     * @return ��������� ������������ ���� ������ � ����������� ������
     */
    protected <O> O readBodyObject(Class<O> bodyType, HttpServletRequest request) throws IOException {
        String bodyContent = request.getReader().lines()
                .collect(Collectors.joining());
        return objectMapper.readValue(bodyContent, bodyType);
    }

    /**
     * ���������� ������ � ���� ������ � ������� JSON
     * @param object - ������, ������� ����� ��������
     * @param response - �����
     */
    protected void writeObjectAsJson(Object object, HttpServletResponse response) throws IOException {
        response.getWriter().println(objectWriter.writeValueAsString(object));
    }
}
