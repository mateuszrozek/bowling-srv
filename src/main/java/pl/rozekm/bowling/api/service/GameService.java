package pl.rozekm.bowling.api.service;

import pl.rozekm.bowling.api.dto.Game;

public interface GameService {
    Game updateGame(Game game, int pinsNumber);
}
