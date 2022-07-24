package pl.rozekm.bowling.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameDTO {
    List<FrameDTO> frames = new ArrayList<>();
}
