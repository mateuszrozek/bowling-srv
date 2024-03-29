package pl.rozekm.bowling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrameDTO {

    private Integer firstRoll;
    private Integer secondRoll;
}
