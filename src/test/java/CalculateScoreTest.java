import com.bluelotussoftware.tomcat.embedded.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculateScoreTest {

    private final GameService gameService = new GameServiceImpl();

    @Test
    public void shouldSumOneNotSpecialThrow(){
        Game game = new Game();
        int pins = 2;

        Game updatedGame = gameService.updateGame(game, pins);

        Assert.assertEquals(pins, (int)updatedGame.getFrames().get(0).getScore());
    }


    @Test
    public void shouldSumTwoNotSpecialThrows(){
        Game game = new Game();
        Frame frame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        frame.setFirstThrow(firstThrow);
        List<Frame> frames = new ArrayList<>();
        frames.add(frame);
        game.setFrames(frames);

        int pins = 3;

        Game updatedGame = gameService.updateGame(game, pins);

        Assert.assertEquals(5, (int)updatedGame.getFrames().get(1).getScore());
        Assert.assertEquals(2, game.getFrames().get(0).getFirstThrow().getPins());
        Assert.assertEquals(3, game.getFrames().get(0).getSecondThrow().getPins());
    }

    @Test
    public void shouldSumThreeNotSpecialThrows(){
        Game game = new Game();
        Frame frame = new Frame();

        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        frame.setFirstThrow(firstThrow);

        Throw secondThrow = new Throw();
        int secondThrowPins = 3;
        secondThrow.setPins(secondThrowPins);
        frame.setSecondThrow(secondThrow);

        List<Frame> frames = new ArrayList<>();
        frames.add(frame);
        game.setFrames(frames);

        int pins = 4;

        Game updatedGame = gameService.updateGame(game, pins);

        Assert.assertEquals(9, (int)updatedGame.getFrames().get(0).getScore());
        Assert.assertEquals(2, game.getFrames().get(0).getFirstThrow().getPins());
        Assert.assertEquals(3, game.getFrames().get(0).getSecondThrow().getPins());
        Assert.assertEquals(4, game.getFrames().get(1).getFirstThrow().getPins());
    }

    @Test
    public void shouldSumTwoThrows(){
        Game game = new Game();
        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
    }
}
