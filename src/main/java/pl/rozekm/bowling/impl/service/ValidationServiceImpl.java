package pl.rozekm.bowling.impl.service;

import pl.rozekm.bowling.api.service.FrameService;
import pl.rozekm.bowling.api.service.ValidationService;
import pl.rozekm.bowling.api.dto.Frame;
import pl.rozekm.bowling.api.dto.Game;

import javax.servlet.http.HttpServletResponse;

import static pl.rozekm.bowling.impl.utils.BowlingConstants.*;

public class ValidationServiceImpl implements ValidationService {

    private final FrameService frameService = new FrameServiceImpl();

    public boolean validateRequest(String pinsParameter, HttpServletResponse response, Game game) throws Exception {
        if (null != game.getFrames() && game.getFrames().size() == MAX_FRAMES && game.getFrames().get(MAX_FRAMES - 1).isScoreKnown()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Game over! Maximal number of houses already played");
            return false;
        }

        try {
            int pins = Integer.parseInt(pinsParameter);
            if (wrongNumberPins(pins, game)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Wrong number of pins! Sum of struck pins is between 0 and 10 in each house");
                return false;
            }
        }
        catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Wrong input passed! Try to pass an integer");
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
