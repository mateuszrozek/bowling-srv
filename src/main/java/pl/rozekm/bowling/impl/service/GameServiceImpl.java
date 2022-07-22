package pl.rozekm.bowling.impl.service;

import pl.rozekm.bowling.api.dto.GameDTO;
import pl.rozekm.bowling.api.service.FrameService;
import pl.rozekm.bowling.api.service.GameService;
import pl.rozekm.bowling.api.service.ScoreService;
import pl.rozekm.bowling.api.dto.Frame;
import pl.rozekm.bowling.api.dto.Game;

import static pl.rozekm.bowling.impl.utils.BowlingConstants.MAX_FRAMES;

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

    @Override
    public GameDTO updateGameDTO(GameDTO gameDTO, int pinsNumber) {
        return gameDTO;
    }

    private boolean isThirdThrowAllowed(Game game) {
        return game.getFrames().size() == MAX_FRAMES && (game.getFrames().get(MAX_FRAMES - 1).isStrike() || game.getFrames().get(MAX_FRAMES - 1).isSpare());
    }
}
