package ru.mikheev.kirill.hw13bank.rest;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.GlobalContext;
import ru.mikheev.kirill.hw13bank.util.contract.PutMoneyRequest;
import ru.mikheev.kirill.hw13bank.util.exception.HttpException;

import java.io.IOException;
import java.util.stream.Collectors;

public class AccountTopUpApiServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String bodyContent = req.getReader().lines()
                    .collect(Collectors.joining());
            PutMoneyRequest request = GlobalContext.getObjectMapper().readValue(bodyContent, PutMoneyRequest.class);
            GlobalContext.getAtmService().putMoney(request.getUser(), request.getMoney());
        } catch (HttpException e) {
            resp.setStatus(e.getHttpErrorCode());
            resp.getWriter().println(e.getMessage());
            e.printStackTrace();
        } catch (UnrecognizedPropertyException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
