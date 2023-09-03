package ru.mikheev.kirill.hw13bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.mikheev.kirill.hw13bank.service.ATMService;
import ru.mikheev.kirill.hw13bank.rest.*;
import ru.mikheev.kirill.hw13bank.rest.general.ErrorHandler;

public class Main {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/");

        context.setAttribute(ContextAttributes.ATM_SERVICE_NAME, new ATMService());
        ObjectMapper objectMapper = new ObjectMapper();
        context.setAttribute(ContextAttributes.OBJECT_MAPPER_NAME, objectMapper);
        context.setAttribute(ContextAttributes.OBJECT_WRITER_NAME, objectMapper.writerWithDefaultPrettyPrinter());

        context.addServlet(UserApiServlet.class, "/user");
        context.addServlet(BalanceApiServlet.class, "/user/balance");
        context.addServlet(AccountTopUpApiServlet.class, "/user/account/add");
        context.addServlet(AccountWithdrawApiServlet.class, "/user/account/sub");
        context.setErrorHandler(new ErrorHandler());

        server.start();
    }
}
