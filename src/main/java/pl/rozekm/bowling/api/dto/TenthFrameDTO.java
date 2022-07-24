package pl.rozekm.bowling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenthFrameDTO extends FrameDTO {
    private Integer thirdRoll;
}
