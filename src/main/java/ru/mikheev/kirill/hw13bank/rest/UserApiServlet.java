package ru.mikheev.kirill.hw13bank.rest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.contract.ChangeUserPinRequest;
import ru.mikheev.kirill.hw13bank.contract.CreateUserRequest;
import ru.mikheev.kirill.hw13bank.contract.DeleteUserRequest;
import ru.mikheev.kirill.hw13bank.dto.UserDto;
import ru.mikheev.kirill.hw13bank.rest.general.AtmHttpServlet;

import java.io.IOException;

public class UserApiServlet extends AtmHttpServlet {

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
        CreateUserRequest request = readBodyObject(CreateUserRequest.class, req);
        writeObjectAsJson(atmService.registerNewClient(request.getUserName()), resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DeleteUserRequest request = readBodyObject(DeleteUserRequest.class, req);
        writeObjectAsJson(
                atmService.unregisterUser(new UserDto(
                        request.getName(),
                        request.getCardNumber(),
                        request.getPin()
                )), resp
        );
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ChangeUserPinRequest request = readBodyObject(ChangeUserPinRequest.class, req);
        atmService.changePin(request.getUser(), request.getNewPin());
    }
}
