package pl.rozekm.bowling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private List<Frame> frames;

    private int currentFrame;

    private Integer overallScore;
}
