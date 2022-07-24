package pl.rozekm.bowling.impl.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import pl.rozekm.bowling.api.dto.FrameDTO;
import pl.rozekm.bowling.api.dto.TenthFrameDTO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FrameServiceImplTest {

    @InjectMocks
    private final FrameServiceImpl frameService = new FrameServiceImpl();

    @Test
    public void testDetermineCurrentFrameDTO_noFrames() {
        ArrayList<FrameDTO> frames = new ArrayList<>();
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(0)));
        assertThat(frames.size(), is(1));
    }

    @Test
    public void testDetermineCurrentFrameDTO_firstFrame_noRollsSaved() {
        ArrayList<FrameDTO> frames = new ArrayList<>(Arrays.asList(new FrameDTO(2, 2), new FrameDTO()));
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(1)));
        assertThat(frames.size(), is(2));
    }

    @Test
    public void testDetermineCurrentFrameDTO_firstFrame_firstRollSaved() {
        ArrayList<FrameDTO> frames = new ArrayList<>(Arrays.asList(new FrameDTO(2, 2), new FrameDTO(1, null)));
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(1)));
        assertThat(frames.size(), is(2));
    }

    @Test
    public void testDetermineCurrentFrameDTO_firstFrame_secondRollSaved() {
        ArrayList<FrameDTO> frames = new ArrayList<>(Arrays.asList(new FrameDTO(2, 2), new FrameDTO(1, 2)));
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(2)));
        assertThat(frames.size(), is(3));
    }

    @Test
    public void testDetermineCurrentFrameDTO_ninthFrame_noRollsSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        FrameDTO frame = frames.get(8);
        frame.setFirstRoll(null);
        frame.setSecondRoll(null);
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(8)));
        assertThat(frames.size(), is(9));
    }


    @Test
    public void testDetermineCurrentFrameDTO_ninthFrame_firstRollSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        FrameDTO frame = frames.get(8);
        frame.setSecondRoll(null);
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(8)));
        assertThat(frames.size(), is(9));
    }

    @Test
    public void testDetermineCurrentFrameDTO_ninthFrame_secondRollSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        FrameDTO frameDTO = frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(9)));
        assertThat(frames.size(), is(10));
    }

    @Test
    public void testDetermineCurrentFrameDTO_tenthFrame_noRollsSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        TenthFrameDTO tenthFrameDTO = new TenthFrameDTO();
        frames.add(tenthFrameDTO);

        TenthFrameDTO frameDTO = (TenthFrameDTO) frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(9)));
        assertThat(frames.size(), is(10));
    }

    @Test
    public void testDetermineCurrentFrameDTO_tenthFrame_firstRollSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        TenthFrameDTO tenthFrameDTO = new TenthFrameDTO();
        tenthFrameDTO.setFirstRoll(1);
        frames.add(tenthFrameDTO);

        TenthFrameDTO frameDTO = (TenthFrameDTO) frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(9)));
        assertThat(frames.size(), is(10));
    }

    @Test
    public void testDetermineCurrentFrameDTO_tenthFrame_secondRollSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        TenthFrameDTO tenthFrameDTO = new TenthFrameDTO();
        tenthFrameDTO.setFirstRoll(1);
        tenthFrameDTO.setSecondRoll(2);
        frames.add(tenthFrameDTO);

        TenthFrameDTO frameDTO = (TenthFrameDTO) frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(9)));
        assertThat(frames.size(), is(10));
    }

    @Test
    public void testDetermineCurrentFrameDTO_tenthFrame_thirdRollSaved() {
        ArrayList<FrameDTO> frames = createFrameDTOList();
        TenthFrameDTO tenthFrameDTO = new TenthFrameDTO();
        tenthFrameDTO.setFirstRoll(1);
        tenthFrameDTO.setSecondRoll(2);
        tenthFrameDTO.setThirdRoll(3);
        frames.add(tenthFrameDTO);

        TenthFrameDTO frameDTO = (TenthFrameDTO) frameService.determineCurrentFrameDTO(frames);

        assertThat(frameDTO, is(notNullValue()));
        assertThat(frameDTO, is(frames.get(9)));
        assertThat(frames.size(), is(10));
    }

    private ArrayList<FrameDTO> createFrameDTOList() {
        ArrayList<FrameDTO> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            FrameDTO frameDTO = new FrameDTO();
            frameDTO.setFirstRoll(1);
            frameDTO.setSecondRoll(2);
            frames.add(frameDTO);
        }
        return frames;
    }
}