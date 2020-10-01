package com.bluelotussoftware.tomcat.embedded.domain;

public interface FrameService {
    Frame determineCurrentFrame(Game game);

    void updateCurrentFrame(Frame currentFrame, int pins);
}
