package pl.rozekm.bowling.api.service;

import pl.rozekm.bowling.api.dto.Game;
import pl.rozekm.bowling.api.dto.GameDTO;

public interface GameService {
    Game updateGame(Game game, int pinsNumber);

    GameDTO updateGameDTO(GameDTO gameDTO, int pinsNumber);

}
