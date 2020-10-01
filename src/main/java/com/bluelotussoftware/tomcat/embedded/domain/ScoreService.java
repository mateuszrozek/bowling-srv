package com.bluelotussoftware.tomcat.embedded.domain;

import java.util.List;

public interface ScoreService {
    Integer calculateScore(List<Frame> frames);
}
