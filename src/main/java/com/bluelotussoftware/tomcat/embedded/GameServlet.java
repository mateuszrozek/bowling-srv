package com.bluelotussoftware.tomcat.embedded;

import com.bluelotussoftware.tomcat.embedded.domain.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "GameServlet")
public class GameServlet extends HttpServlet {

    private final GameService gameService = new GameServiceImpl();
    private final ValidationService validationService = new ValidationServiceImpl();

    private Game inMemoryGame = new Game();

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        setAccessControlHeaders(response);
        PrintWriter out = response.getWriter();
        String pinsParameter = request.getParameter("pins");
        int pins = Integer.parseInt(pinsParameter);

        if (validationService.validateRequest(pins, response, inMemoryGame)) {

            Game updatedGame = gameService.updateGame(inMemoryGame, pins);
            inMemoryGame = updatedGame;

            String gameJsonString = new Gson().toJson(updatedGame);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(gameJsonString);
        }

        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        setAccessControlHeaders(response);
        inMemoryGame = new Game();
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
    }
}