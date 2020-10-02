import com.bluelotussoftware.tomcat.embedded.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DetermineCurrentFrameTest {

    private final FrameService frameService = new FrameServiceImpl();


    @Test
    public void shouldDetermineFirstFrameWhenGameStarts() {

        Game game = new Game();

        Frame determinedFrame = frameService.determineCurrentFrame(game);

        Assert.assertEquals(1, game.getFrames().size());
        Assert.assertNotNull(game.getFrames().get(0));
        Assert.assertEquals(game.getFrames().get(0), determinedFrame);

    }

    @Test
    public void shouldDetermineFirstFrameAfterFirstThrow() {

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        firstFrame.setFirstThrow(firstThrow);

        List<Frame> frames = new ArrayList<>();
        frames.add(firstFrame);

        Game game = new Game();
        game.setFrames(frames);

        Frame determinedFrame = frameService.determineCurrentFrame(game);

        Assert.assertEquals(1, game.getFrames().size());
        Assert.assertNotNull(game.getFrames().get(0));
        Assert.assertEquals(game.getFrames().get(0), determinedFrame);
    }

    @Test
    public void shouldDetermineNextFrameAfterTwoThrows() {

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        Throw secondThrow = new Throw();

        firstFrame.setFirstThrow(firstThrow);
        firstFrame.setSecondThrow(secondThrow);

        List<Frame> frames = new ArrayList<>();
        frames.add(firstFrame);

        Game game = new Game();
        game.setFrames(frames);

        Frame determinedFrame = frameService.determineCurrentFrame(game);

        Assert.assertEquals(2, game.getFrames().size());
        Assert.assertNotNull(game.getFrames().get(1));
        Assert.assertEquals(game.getFrames().get(1), determinedFrame);
    }

    @Test
    public void shouldDetermineNextFrameAfterStrike() {
        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 10;

        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);
        firstFrame.setStrike(true);

        List<Frame> frames = new ArrayList<>();
        frames.add(firstFrame);

        Game game = new Game();
        game.setFrames(frames);

        Frame determinedFrame = frameService.determineCurrentFrame(game);

        Assert.assertEquals(2, game.getFrames().size());
        Assert.assertEquals(determinedFrame, game.getFrames().get(1));
    }

    @Test
    public void shouldDetermineNextFrameWhenNinthIsFull() {
        List<Frame> frames = generateFrames(9);

        Game game = new Game();
        game.setFrames(frames);

        Frame determinedFrame = frameService.determineCurrentFrame(game);

        Assert.assertEquals(10, game.getFrames().size());
        Assert.assertEquals(determinedFrame, game.getFrames().get(9));
    }

    @Test
    public void shouldDetermineNextFrameWhenTenthIsNotFull() {
        List<Frame> frames = generateFrames(8);
        Throw firstThrow = new Throw();
        firstThrow.setPins(2);
        Frame frame = new Frame();
        frame.setFirstThrow(firstThrow);
        frames.add(frame);

        Game game = new Game();
        game.setFrames(frames);

        Frame determinedFrame = frameService.determineCurrentFrame(game);

        Assert.assertEquals(10, game.getFrames().size());
        Assert.assertEquals(determinedFrame, game.getFrames().get(9));
    }

    private List<Frame> generateFrames(int number) {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < number+1; i++) {
            Throw firstThrow = new Throw();
            firstThrow.setPins(10);
            Frame frame = new Frame();
            frame.setFirstThrow(firstThrow);
            frames.add(frame);
        }
        return frames;
    }
}
