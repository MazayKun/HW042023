package ru.mikheev.kirill.hw13bank;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.mikheev.kirill.hw13bank.rest.AccountTopUpApiServlet;
import ru.mikheev.kirill.hw13bank.rest.AccountWithdrawApiServlet;
import ru.mikheev.kirill.hw13bank.rest.BalanceApiServlet;
import ru.mikheev.kirill.hw13bank.rest.UserApiServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(UserApiServlet.class, "/user");
        context.addServlet(BalanceApiServlet.class, "/user/balance");
        context.addServlet(AccountTopUpApiServlet.class, "/user/account/add");
        context.addServlet(AccountWithdrawApiServlet.class, "/user/account/sub");

        GlobalContext.init();
        server.start();
    }
}
