package com.bluelotussoftware.tomcat.embedded.domain;

import static com.bluelotussoftware.tomcat.embedded.domain.BowlingConstants.MAX_FRAMES;

public class GameServiceImpl implements GameService {

    private final FrameService frameService = new FrameServiceImpl();
    private final ScoreService scoreService = new ScoreServiceImpl();

    @Override
    public Game updateGame(Game game, int pins) {

        Frame currentFrame = frameService.determineCurrentFrame(game);
        if (!currentFrame.isStrike() || isThirdThrowAllowed(game)) {
            frameService.updateCurrentFrame(currentFrame, pins);
        }
        Integer score = scoreService.calculateScore(game.getFrames());
        game.setOverallScore(score);

        return game;
    }

    private boolean isThirdThrowAllowed(Game game) {
        return game.getFrames().size() == MAX_FRAMES && (game.getFrames().get(MAX_FRAMES - 1).isStrike() || game.getFrames().get(MAX_FRAMES - 1).isSpare());
    }
}
