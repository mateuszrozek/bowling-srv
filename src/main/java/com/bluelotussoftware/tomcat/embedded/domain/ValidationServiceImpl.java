package com.bluelotussoftware.tomcat.embedded.domain;

import javax.servlet.http.HttpServletResponse;

import static com.bluelotussoftware.tomcat.embedded.domain.BowlingConstants.*;

public class ValidationServiceImpl implements ValidationService {

    private final FrameService frameService = new FrameServiceImpl();

    public boolean validateRequest(int pins, HttpServletResponse response, Game game) throws Exception {
        if (null != game.getFrames() && game.getFrames().size() == MAX_FRAMES && game.getFrames().get(MAX_FRAMES - 1).isScoreKnown()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Game over! Maximal number of houses already played");
            return false;
        }
        if (wrongNumberPins(pins, game)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Wrong number of pins! Sum of struck pins is between 0 and 10 in each house");
            return false;
        }
        return true;
    }

    private boolean wrongNumberPins(int pins, Game game) {
        boolean wrongNumber = false;
        Frame currentFrame = frameService.determineCurrentFrame(game);
        if (null != currentFrame.getFirstThrow() && sumFrame(pins, currentFrame) > MAX_PINS && !currentFrame.isLastFrame()) {
            wrongNumber = true;
        }
        if (pins < MINIMUM_PINS || pins > MAX_PINS) {
            wrongNumber = true;
        }
        return wrongNumber;
    }

    private int sumFrame(int pins, Frame currentFrame) {
        return currentFrame.getFirstThrow().getPins() + pins;
    }
}
