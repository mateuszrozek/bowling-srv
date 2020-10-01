package com.bluelotussoftware.tomcat.embedded.domain;

public class GameServiceImpl implements GameService {

    private static final int NORMAL_FRAMES = 9;
    private static final int MAX_FRAMES = 10;
    private final FrameService frameService = new FrameServiceImpl();
    private final ScoreService scoreService = new ScoreServiceImpl();

    @Override
    public Game updateGame(Game game, int pins) {

        Frame currentFrame = frameService.determineCurrentFrame(game);
        if (!currentFrame.isStrike() || !isThirdThrowAllowed(game)) {
            frameService.updateCurrentFrame(currentFrame, pins);
        }
//        if (!currentFrame.isStrike()) {
//            frameService.updateCurrentFrame(currentFrame, pins);
//        }
        Integer score = scoreService.calculateScore(game.getFrames());
        game.setOverallScore(score);

        return game;
    }

    private boolean isThirdThrowAllowed(Game game) {
        return game.getFrames().size() == MAX_FRAMES && (game.getFrames().get(MAX_FRAMES - 1).isStrike() || game.getFrames().get(MAX_FRAMES - 1).isSpare());
    }
}
