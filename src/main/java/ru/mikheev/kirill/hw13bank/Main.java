package ru.mikheev.kirill.hw13bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.mikheev.kirill.hw13bank.rest.AccountTopUpApiServlet;
import ru.mikheev.kirill.hw13bank.rest.AccountWithdrawApiServlet;
import ru.mikheev.kirill.hw13bank.rest.BalanceApiServlet;
import ru.mikheev.kirill.hw13bank.rest.UserApiServlet;
import ru.mikheev.kirill.hw13bank.rest.general.ErrorHandler;
import ru.mikheev.kirill.hw13bank.service.ATMService;

import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try(InputStream stream = Main.class.getResourceAsStream("/WEB-INF/application.properties")) {
            properties.load(stream);
        }
        Server server = new Server(Integer.parseInt(properties.getProperty("server.port")));
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
