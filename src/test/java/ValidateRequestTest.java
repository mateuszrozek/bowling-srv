import pl.rozekm.bowling.api.service.ValidationService;
import pl.rozekm.bowling.impl.service.ValidationServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class ValidateRequestTest {
    private final ValidationService validationService = new ValidationServiceImpl();

    @Test
    public void testMaximalHousesPlayed() throws IOException {
//
//
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//
//        validationService.validateRequest(2, response, new Game());
    }


    @Test
    public void testWrongSumOfPins(){

    }

    @Test
    public void testPinsMoreAsMax(){

    }

    @Test
    public void testNegativePins(){

    }

}
