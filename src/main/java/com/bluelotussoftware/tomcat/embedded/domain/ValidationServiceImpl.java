package com.bluelotussoftware.tomcat.embedded.domain;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidationServiceImpl implements ValidationService {

    public static final int MAX_HOUSES = 10;
    public static final int MAX_PINS = 10;
    public static final int MINIMUM_PINS = 0;
    private final FrameService frameService = new FrameServiceImpl();

    public boolean validateRequest(int pins, HttpServletResponse response, Game game) throws IOException {
        boolean requestValid = true;
        if (null != game.getFrames() && game.getFrames().size() > MAX_HOUSES) {
            response.setStatus(500);
            response.getWriter().println("<html><body><p>Game over! Maximal number of houses already played!</p></body></html>");
            requestValid = false;
        }
        if (wrongNumberPins(pins, game)) {
            response.setStatus(400);
            response.getWriter().println("<html><body><p>Bad request! Sum of stroke pins is between 0 and 10 in each house!</p></body></html>");
            requestValid = false;
        }
        return requestValid;
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
