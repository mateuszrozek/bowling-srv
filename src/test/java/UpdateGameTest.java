import com.bluelotussoftware.tomcat.embedded.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UpdateGameTest {

    private final GameService gameService = new GameServiceImpl();

    @Test
    public void shouldUpdateGameAfterThrow(){
        Game game = new Game();
        int pins = 2;

        Game updatedGame = gameService.updateGame(game, pins);

        Assert.assertNotNull(updatedGame.getFrames());
        Assert.assertEquals(1, updatedGame.getFrames().size());
        Assert.assertNotNull(updatedGame.getFrames().get(0));
        Assert.assertNotNull(updatedGame.getFrames().get(0).getFirstThrow());
        Assert.assertEquals(2, updatedGame.getFrames().get(0).getFirstThrow().getPins());
    }

    @Test
    public void shouldUpdateGameAfterStrike(){
        Game game = new Game();

        List<Frame> frames = new ArrayList<>();

        Frame firstFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 10;
        firstThrow.setPins(firstThrowPins);
        firstFrame.setFirstThrow(firstThrow);
        firstFrame.setStrike(true);
        frames.add(firstFrame);
        game.setFrames(frames);

        int pins = 2;

        Game updatedGame = gameService.updateGame(game, pins);

        Assert.assertNotNull(updatedGame.getFrames());
        Assert.assertEquals(2, updatedGame.getFrames().size());
        Assert.assertNotNull(updatedGame.getFrames().get(0));
        Assert.assertNotNull(updatedGame.getFrames().get(1));
    }
}
