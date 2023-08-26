package ru.mikheev.kirill.hw13bank.rest;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.GlobalContext;
import ru.mikheev.kirill.hw13bank.util.dto.BalanceDto;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;
import ru.mikheev.kirill.hw13bank.util.exception.HttpException;

import java.io.IOException;

public class BalanceApiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        try {
            BalanceDto balance = GlobalContext.getAtmService().getBalance(
                    new UserDto(
                            req.getParameter("name"),
                            req.getParameter("cardNumber"),
                            req.getParameter("pin")
                    )
            );
            resp.getWriter().println(GlobalContext.getObjectWriter().writeValueAsString(balance));
        }catch (HttpException e) {
            resp.setStatus(e.getHttpErrorCode());
            resp.getWriter().println(e.getMessage());
            e.printStackTrace();
        }catch (UnrecognizedPropertyException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
