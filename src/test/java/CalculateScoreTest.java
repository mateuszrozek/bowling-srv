import pl.rozekm.bowling.api.dto.Frame;
import pl.rozekm.bowling.api.service.ScoreService;
import pl.rozekm.bowling.impl.service.ScoreServiceImpl;
import pl.rozekm.bowling.api.dto.Throw;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculateScoreTest {

    private final ScoreService scoreService = new ScoreServiceImpl();

    @Test
    public void shouldNotCalculateScoreAfterFirstNormalThrow() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 4;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);
        frames.add(firstFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNull(score);
        Assert.assertFalse(frames.get(0).isScoreKnown());
        Assert.assertNull(frames.get(0).getScore());
    }

    @Test
    public void shouldCalculateScoreAfterSecondNormalThrow() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 4;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);

        Throw secondThrow = new Throw();
        int secondThrowPins = 4;
        secondThrow.setPins(secondThrowPins);
        firstFrame.setSecondThrow(secondThrow);

        frames.add(firstFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNotNull(score);
        Assert.assertEquals(8, (int)frames.get(0).getScore());
        Assert.assertTrue(frames.get(0).isScoreKnown());
    }

    @Test
    public void shouldNotCalculateScoreAfterStrikeThrow() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 10;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);

        frames.add(firstFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNull(score);
        Assert.assertNull(frames.get(0).getScore());
        Assert.assertFalse(frames.get(0).isScoreKnown());
    }

    @Test
    public void shouldNotCalculateScoreAfterStrikeThrowAndNormalThrow() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 10;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);

        frames.add(firstFrame);

        Frame secondFrame = new Frame();
        Throw secondThrow = new Throw();
        int secondThrowPins = 4;
        secondThrow.setPins(secondThrowPins);
        secondFrame.setFirstThrow(secondThrow);

        frames.add(secondFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNull(score);
        Assert.assertNull(frames.get(0).getScore());
        Assert.assertFalse(frames.get(0).isScoreKnown());
    }

    @Test
    public void shouldNotCalculateScoreAfterStrikeThrowAndTwoNormalThrows() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        firstFrame.setStrike(true);
        Throw firstThrow = new Throw();
        int firstThrowPins = 10;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);

        frames.add(firstFrame);

        Frame secondFrame = new Frame();
        Throw secondThrow = new Throw();
        int secondThrowPins = 4;
        secondThrow.setPins(secondThrowPins);
        secondFrame.setFirstThrow(secondThrow);
        Throw thirdThrow = new Throw();
        int thirdThrowPins = 4;
        thirdThrow.setPins(thirdThrowPins);
        secondFrame.setSecondThrow(secondThrow);

        frames.add(secondFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNotNull(score);
        Assert.assertNotNull(frames.get(0).getScore());
        Assert.assertNotNull(frames.get(1).getScore());
        Assert.assertEquals(18 , (int)frames.get(0).getScore());
        Assert.assertEquals(26 , (int)frames.get(1).getScore());
        Assert.assertTrue(frames.get(0).isScoreKnown());
        Assert.assertTrue(frames.get(1).isScoreKnown());
    }

    @Test
    public void shouldNotCalculateScoreAfterSpareThrow() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 4;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);
        Throw secondThrow = new Throw();
        int secondThrowPins = 6;
        secondThrow.setPins(secondThrowPins);
        firstFrame.setSecondThrow(secondThrow);
        firstFrame.setSpare(true);

        frames.add(firstFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNull(score);
        Assert.assertNull(frames.get(0).getScore());
        Assert.assertFalse(frames.get(0).isScoreKnown());
    }

    @Test
    public void shouldCalculateScoreAfterSpareThrowAndNormalThrow() {

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 4;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);
        Throw secondThrow = new Throw();
        int secondThrowPins = 6;
        secondThrow.setPins(secondThrowPins);
        firstFrame.setSecondThrow(secondThrow);
        firstFrame.setSpare(true);
        frames.add(firstFrame);

        Frame secondFrame = new Frame();
        Throw thirdThrow = new Throw();
        int thirdThrowPins = 5;
        thirdThrow.setPins(thirdThrowPins);
        secondFrame.setFirstThrow(thirdThrow);

        frames.add(secondFrame);

        Integer score = scoreService.calculateScore(frames);

        Assert.assertNull(score);
        Assert.assertNotNull(frames.get(0).getScore());
        Assert.assertNull(frames.get(1).getScore());
        Assert.assertTrue(frames.get(0).isScoreKnown());
        Assert.assertFalse(frames.get(1).isScoreKnown());
        Assert.assertEquals(15 , (int)frames.get(0).getScore());
    }


}
