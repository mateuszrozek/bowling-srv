package com.bluelotussoftware.tomcat.embedded;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.rozekm.bowling.api.dto.FrameDTO;
import pl.rozekm.bowling.api.dto.GameDTO;
import pl.rozekm.bowling.api.service.GameService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BowlingServletTest extends TestCase {

    @InjectMocks
    private final BowlingServlet bowlingServlet = new BowlingServlet();

    @Mock
    private GameService gameService;

    @Test
    public void testDoPost() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        GameDTO gameDTO = createGameDTO();
        when(request.getParameter("game")).thenReturn(createGameString(gameDTO));
        when(request.getParameter("pins")).thenReturn("5");
        //test

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        when(gameService.updateGameDTO(any(), anyInt())).thenReturn(updateGame(gameDTO));

        bowlingServlet.doPost(request, response);

        String result = stringWriter.toString();
        assertThat(result, is(notNullValue()));
        assertThat(result, is("{\"frames\":[{\"firstRoll\":5,\"secondRoll\":0}]}"));
    }

    private GameDTO updateGame(GameDTO gameDTO) {
        FrameDTO frameDTO = new FrameDTO();
        frameDTO.setFirstRoll(5);
        gameDTO.setFrames(Collections.singletonList(frameDTO));
        return gameDTO;
    }

    private String createGameString(GameDTO gameDTO) {
        return new Gson().toJson(gameDTO);
    }

    private GameDTO createGameDTO() {
        return new GameDTO();
    }

    private PrintWriter createPrintWriter() {
        StringWriter out = new StringWriter();
        return new PrintWriter(out);
    }
}