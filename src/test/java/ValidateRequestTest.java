import com.bluelotussoftware.tomcat.embedded.domain.ValidationService;
import com.bluelotussoftware.tomcat.embedded.domain.ValidationServiceImpl;
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
