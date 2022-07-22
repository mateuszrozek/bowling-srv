package com.bluelotussoftware.tomcat.embedded;

import com.google.gson.Gson;
import pl.rozekm.bowling.api.dto.GameDTO;
import pl.rozekm.bowling.api.service.GameService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BowlingServlet")
public class BowlingServlet extends HttpServlet {

    @Inject
    private GameService gameService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setAccessControlHeaders(response);
        String gameString = request.getParameter("game");
        String pinsString = request.getParameter("pins");

        GameDTO gameDTO = new Gson().fromJson(gameString, GameDTO.class);
        int pins = Integer.parseInt(pinsString);


        GameDTO updateGame = gameService.updateGameDTO(gameDTO, pins);

        String updatedGameString = new Gson().toJson(updateGame);

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(updatedGameString);
            out.flush();
        }
    }


    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
    }
}
