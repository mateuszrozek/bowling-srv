package pl.rozekm.bowling.api.service;

import pl.rozekm.bowling.api.dto.Frame;
import pl.rozekm.bowling.api.dto.FrameDTO;
import pl.rozekm.bowling.api.dto.Game;

import java.util.List;

public interface FrameService {
    Frame determineCurrentFrame(Game game);

    void updateCurrentFrame(Frame currentFrame, int pins);

    FrameDTO determineCurrentFrameDTO(List<FrameDTO> frames);
}
