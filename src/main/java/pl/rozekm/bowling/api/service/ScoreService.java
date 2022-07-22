package pl.rozekm.bowling.api.service;

import pl.rozekm.bowling.api.dto.Frame;

import java.util.List;

public interface ScoreService {
    Integer calculateScore(List<Frame> frames);
}
