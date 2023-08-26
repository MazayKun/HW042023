package ru.mikheev.kirill.hw13bank.rest;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.GlobalContext;
import ru.mikheev.kirill.hw13bank.util.contract.ChangeUserPinRequest;
import ru.mikheev.kirill.hw13bank.util.contract.CreateUserRequest;
import ru.mikheev.kirill.hw13bank.util.contract.DeleteUserRequest;
import ru.mikheev.kirill.hw13bank.util.converter.RequestConverter;
import ru.mikheev.kirill.hw13bank.util.dto.MoneyBundleDto;
import ru.mikheev.kirill.hw13bank.util.dto.UserDto;
import ru.mikheev.kirill.hw13bank.util.exception.HttpException;

import java.io.IOException;
import java.util.stream.Collectors;

public class UserApiServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String bodyContent = req.getReader().lines()
                    .collect(Collectors.joining());
            CreateUserRequest request = GlobalContext.getObjectMapper().readValue(bodyContent, CreateUserRequest.class);
            UserDto userDto = GlobalContext.getAtmService().registerNewClient(request.getUserName());
            resp.getWriter().println(GlobalContext.getObjectWriter().writeValueAsString(userDto));
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String bodyContent = req.getReader().lines()
                    .collect(Collectors.joining());
            DeleteUserRequest request = GlobalContext.getObjectMapper().readValue(bodyContent, DeleteUserRequest.class);
            MoneyBundleDto moneyBundleDto = GlobalContext.getAtmService().unregisterUser(RequestConverter.toDto(request));
            resp.getWriter().println(GlobalContext.getObjectWriter().writeValueAsString(moneyBundleDto));
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

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String bodyContent = req.getReader().lines()
                    .collect(Collectors.joining());
            ChangeUserPinRequest request = GlobalContext.getObjectMapper().readValue(bodyContent, ChangeUserPinRequest.class);
            GlobalContext.getAtmService().changePin(request.getUser(), request.getNewPin());
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
