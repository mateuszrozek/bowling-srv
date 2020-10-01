package com.bluelotussoftware.tomcat.embedded.domain;

import java.util.List;
import java.util.Optional;

public class ScoreServiceImpl implements ScoreService {
    @Override
    public Integer calculateScore(List<Frame> frames) {

        int score = 0;

        for (int i = 0; i < frames.size(); i++) {
            Frame currentFrame = frames.get(i);
            currentFrame.setScoreKnown(false);

            if (i != 0) {
                Frame previousFrame = frames.get(i - 1);
                if (previousFrame.isStrike()) {
                    score += sumFrame(currentFrame);
                    if (currentFrame.getSecondThrow() != null) {
                        previousFrame.setScoreKnown(true);
                        previousFrame.setScore(score);
                    }
                }
                if (previousFrame.isSpare()) {
                    score += currentFrame.getFirstThrow().getPins();
                    previousFrame.setScoreKnown(true);
                    if (previousFrame.isScoreKnown()) {
                        previousFrame.setScore(score);
                    }
                }
            }
            score += sumFrame(currentFrame);
            if (isFrameFull(currentFrame) || thirdThrowNotAllowed(currentFrame) || isLastFrameFull(currentFrame)) {
                currentFrame.setScoreKnown(true);
                currentFrame.setScore(score);
            }

        }
        Integer returnValue = determinePrintedScore(frames.get(frames.size() - 1).isScoreKnown(), score);
        return Optional.ofNullable(returnValue).orElse(null);
    }

    private boolean isLastFrameFull(Frame currentFrame) {
        return null != currentFrame.getBonusThrow();
    }

    private boolean thirdThrowNotAllowed(Frame currentFrame) {
        return !currentFrame.isLastFrame() && null != currentFrame.getSecondThrow() && currentFrame.getSecondThrow().getPins() < 10;
    }

    private boolean isFrameFull(Frame currentFrame) {
        return !currentFrame.isStrike() && !currentFrame.isSpare() && currentFrame.getSecondThrow() != null;
    }

    private Integer determinePrintedScore(boolean scoreKnown, int score) {
        if (scoreKnown) {
            return score;
        } else {
            return null;
        }
    }

    private int sumFrame(Frame frame) {
        int sum = frame.getFirstThrow().getPins();
        if (null != frame.getSecondThrow()) {
            sum += frame.getSecondThrow().getPins();
        }
        if (null != frame.getBonusThrow()){
            sum += frame.getBonusThrow().getPins();
        }
        return sum;
    }
}
