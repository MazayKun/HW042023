package ru.mikheev.kirill.hw13bank.rest.general;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import ru.mikheev.kirill.hw13bank.exception.HttpException;

import java.io.IOException;
import java.io.PrintWriter;

import static jakarta.servlet.RequestDispatcher.*;

/**
 * Servlet exceptions handler
 */
public class ErrorHandler extends ErrorPageErrorHandler {

    @Override
    @SuppressWarnings("unchecked")
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            Class<Exception> exceptionType = (Class<Exception>)request.getAttribute(ERROR_EXCEPTION_TYPE);
            Exception exception = (Exception) request.getAttribute(ERROR_EXCEPTION);
            exception.printStackTrace();
            if(HttpException.class.isAssignableFrom(exceptionType)) {
                writer.write(exception.getMessage());
                response.setStatus(((HttpException)exception).getHttpErrorCode());
                return;
            }
            if(exceptionType.equals(UnrecognizedPropertyException.class)) {
                writer.write("Wrong body construction");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            writer.write("Internal server error");
        }
    }
}
