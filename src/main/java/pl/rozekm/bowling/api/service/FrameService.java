package pl.rozekm.bowling.api.service;

import pl.rozekm.bowling.api.dto.Frame;
import pl.rozekm.bowling.api.dto.Game;

public interface FrameService {
    Frame determineCurrentFrame(Game game);

    void updateCurrentFrame(Frame currentFrame, int pins);
}
