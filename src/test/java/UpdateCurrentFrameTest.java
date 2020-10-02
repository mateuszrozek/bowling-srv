import com.bluelotussoftware.tomcat.embedded.domain.Frame;
import com.bluelotussoftware.tomcat.embedded.domain.FrameService;
import com.bluelotussoftware.tomcat.embedded.domain.FrameServiceImpl;
import com.bluelotussoftware.tomcat.embedded.domain.Throw;
import org.junit.Assert;
import org.junit.Test;

public class UpdateCurrentFrameTest {

    private final FrameService frameService = new FrameServiceImpl();


    @Test
    public void shouldSetPinsForFirstThrow() {

        Frame currentFrame = new Frame();
        int pins = 1;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertNotNull(currentFrame.getFirstThrow());
        Assert.assertEquals(pins, currentFrame.getFirstThrow().getPins());
        Assert.assertNull(currentFrame.getSecondThrow());

    }

    @Test
    public void shouldSetPinsForSecondThrow() {
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

    @Test
    public void shouldSetPinsInFirstThrowLastFrame() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        int pins = 2;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertNotNull(currentFrame.getFirstThrow());
        Assert.assertEquals(pins, currentFrame.getFirstThrow().getPins());
        Assert.assertNull(currentFrame.getSecondThrow());
    }

    @Test
    public void shouldSetPinsInSecondThrowLastFrame() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);
        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        int pins = 3;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertNotNull(currentFrame.getSecondThrow());
        Assert.assertEquals(pins, currentFrame.getSecondThrow().getPins());
    }


    @Test
    public void shouldNotSetPinsInThirdThrowLastFrame() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        Throw secondThrow = new Throw();
        int secondThrowPins = 2;
        secondThrow.setPins(secondThrowPins);
        currentFrame.setSecondThrow(secondThrow);

        int pins = 3;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertNotNull(currentFrame.getFirstThrow());
        Assert.assertNotNull(currentFrame.getSecondThrow());
        Assert.assertNotNull(currentFrame.getBonusThrow());
    }

    @Test
    public void shouldSetLastFrameAsStrikeFirstThrow() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        int pins = 10;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertTrue(currentFrame.isStrike());
        Assert.assertFalse(currentFrame.isSpare());
    }

    @Test
    public void shouldSetLastFrameAsStrikeSecondThrow() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        int pins = 10;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertTrue(currentFrame.isStrike());
        Assert.assertFalse(currentFrame.isSpare());
    }

    @Test
    public void shouldSetLastFrameAsStrikeThirdThrow() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        Throw secondThrow = new Throw();
        int secondThrowPins = 2;
        secondThrow.setPins(secondThrowPins);
        currentFrame.setSecondThrow(secondThrow);

        int pins = 10;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertTrue(currentFrame.isStrike());
        Assert.assertFalse(currentFrame.isSpare());
    }

    @Test
    public void shouldNotSetFrameAsSpareFirstThrow() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        int pins = 10;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertFalse(currentFrame.isSpare());
        Assert.assertTrue(currentFrame.isStrike());
    }

    @Test
    public void shouldSetFrameAsSpareSecondThrow() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        int pins = 8;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertTrue(currentFrame.isSpare());
        Assert.assertFalse(currentFrame.isStrike());
    }

    @Test
    public void shouldSetFrameAsSpareThirdThrow() {
        Frame currentFrame = new Frame();
        currentFrame.setLastFrame(true);

        Throw firstThrow = new Throw();
        int firstThrowPins = 2;
        firstThrow.setPins(firstThrowPins);
        currentFrame.setFirstThrow(firstThrow);

        Throw secondThrow = new Throw();
        int secondThrowPins = 2;
        secondThrow.setPins(secondThrowPins);
        currentFrame.setSecondThrow(secondThrow);

        int pins = 8;

        frameService.updateCurrentFrame(currentFrame, pins);

        Assert.assertTrue(currentFrame.isSpare());
        Assert.assertFalse(currentFrame.isStrike());
    }

}
