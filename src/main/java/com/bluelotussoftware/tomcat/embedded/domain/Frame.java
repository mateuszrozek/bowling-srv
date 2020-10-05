package com.bluelotussoftware.tomcat.embedded.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Frame {

    private Throw firstThrow;
    private Throw secondThrow;
    private Throw bonusThrow;
    private boolean isStrike;
    private boolean isSpare;
    private boolean isScoreKnown;
    private boolean isLastFrame;
    private Integer score;
}
