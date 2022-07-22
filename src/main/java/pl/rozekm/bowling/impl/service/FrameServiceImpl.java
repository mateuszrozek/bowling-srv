package pl.rozekm.bowling.impl.service;

import pl.rozekm.bowling.api.service.FrameService;
import pl.rozekm.bowling.api.dto.Frame;
import pl.rozekm.bowling.api.dto.Game;
import pl.rozekm.bowling.api.dto.Throw;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import static pl.rozekm.bowling.impl.utils.BowlingConstants.*;

public class FrameServiceImpl implements FrameService {

    @Override
    public Frame determineCurrentFrame(Game game) {
        Preconditions.checkNotNull(game);

        Frame currentFrame;
        List<Frame> frames = game.getFrames();
        if (null == frames || frames.isEmpty()) {
            frames = new ArrayList<>();
            currentFrame = processNewFrame(game, frames);
        } else {
            Frame candidateFrame = frames.get(frames.size() - 1);
            if ((null == candidateFrame.getSecondThrow() && !candidateFrame.isStrike()) || frames.size() == MAX_FRAMES) {
                currentFrame = candidateFrame;
            } else {
                currentFrame = processNewFrame(game, frames);
            }
        }
        return currentFrame;
    }

    private Frame processNewFrame(Game game, List<Frame> frames) {
        Frame currentFrame = new Frame();

        if (frames.size() == NORMAL_FRAMES) {
            currentFrame.setLastFrame(true);
        }
        frames.add(currentFrame);
        game.setFrames(frames);
        return currentFrame;
    }

    @Override
    public void updateCurrentFrame(Frame currentFrame, int pins) {
        Preconditions.checkNotNull(currentFrame);

        Throw currentThrow = new Throw();
        currentThrow.setPins(pins);

        if (currentFrame.isLastFrame()) {
            processLastFrame(currentFrame, currentThrow);
        } else {
            processNormalFrame(currentFrame, currentThrow);
        }
    }

    private void processNormalFrame(Frame currentFrame, Throw currentThrow) {
        if (null == currentFrame.getFirstThrow()) {
            currentFrame.setFirstThrow(currentThrow);
            determineIfStrike(currentFrame, currentThrow);
        } else {
            currentFrame.setSecondThrow(currentThrow);
            determineIfSpare(currentFrame);
        }
    }

    private void processLastFrame(Frame currentFrame, Throw currentThrow) {
        if (null == currentFrame.getFirstThrow()) {
            currentFrame.setFirstThrow(currentThrow);
            determineIfStrike(currentFrame, currentThrow);
        } else if (null == currentFrame.getSecondThrow()) {
            currentFrame.setSecondThrow(currentThrow);
            determineIfStrike(currentFrame, currentThrow);
            if (!currentFrame.isStrike()) {
                determineIfSpare(currentFrame);
            }
        } else {
            currentFrame.setBonusThrow(currentThrow);
            determineIfStrike(currentFrame, currentThrow);
            if (!currentFrame.isStrike()) {
                determineIfLastSpare(currentFrame);
            }
        }
    }

    private void determineIfStrike(Frame currentFrame, Throw currentThrow) {
        if (currentThrow.getPins() == MAX_PINS) {
            currentFrame.setStrike(true);
        }
    }

    private void determineIfSpare(Frame currentFrame) {
        int firstThrow = currentFrame.getFirstThrow().getPins();
        int secondThrow = currentFrame.getSecondThrow().getPins();
        if (Math.addExact(firstThrow, secondThrow) == MAX_PINS) {
            currentFrame.setSpare(true);
        }
    }

    private void determineIfLastSpare(Frame currentFrame) {
        int secondThrow = currentFrame.getSecondThrow().getPins();
        int bonusThrow = currentFrame.getBonusThrow().getPins();
        if (Math.addExact(secondThrow, bonusThrow) == MAX_PINS) {
            currentFrame.setSpare(true);
        }
    }
}
