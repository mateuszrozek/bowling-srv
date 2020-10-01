import com.bluelotussoftware.tomcat.embedded.domain.Frame;
import com.bluelotussoftware.tomcat.embedded.domain.FrameService;
import com.bluelotussoftware.tomcat.embedded.domain.FrameServiceImpl;
import com.bluelotussoftware.tomcat.embedded.domain.Throw;
import org.junit.Assert;
import org.junit.Test;

public class UpdateCurrentFrameTest {

    private final FrameService frameService = new FrameServiceImpl();


    @Test
    public void shouldSetScoreForFirstThrow() {

        Frame currentFrame = new Frame();
        int pins = 1;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertNotNull(currentFrame.getFirstThrow());
        Assert.assertEquals(pins, currentFrame.getFirstThrow().getPins());
        Assert.assertNull(currentFrame.getSecondThrow());

    }

    @Test
    public void shouldSetScoreForSecondThrow() {
        Frame currentFrame = new Frame();
        Throw firstThrow = new Throw();
        currentFrame.setFirstThrow(firstThrow);

        int pins = 1;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertNotNull(currentFrame.getSecondThrow());
        Assert.assertEquals(pins, currentFrame.getSecondThrow().getPins());
    }

    @Test
    public void shouldSetFrameAsStrike() {
        Frame currentFrame = new Frame();
//        Throw firstThrow = new Throw();
//        currentFrame.setFirstThrow(firstThrow);

        int pins = 10;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertTrue(currentFrame.isStrike());
        Assert.assertFalse(currentFrame.isSpare());
    }

    @Test
    public void shouldSetFrameAsSpare() {
        Frame currentFrame = new Frame();
        Throw firstThrow = new Throw();
        int firstThrowPins = 9;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        int secondThrowPins = 1;

        frameService.updateCurrentFrame(currentFrame, secondThrowPins);

        Assert.assertTrue(currentFrame.isSpare());
        Assert.assertFalse(currentFrame.isStrike());
    }
}
