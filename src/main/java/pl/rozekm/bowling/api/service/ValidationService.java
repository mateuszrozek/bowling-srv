package pl.rozekm.bowling.api.service;

import pl.rozekm.bowling.api.dto.Game;

import javax.servlet.http.HttpServletResponse;

public interface ValidationService {
    boolean validateRequest(String pins, HttpServletResponse response, Game game) throws Exception;
}
