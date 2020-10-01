package com.bluelotussoftware.tomcat.embedded.domain;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ValidationService {
    boolean validateRequest(int pins, HttpServletResponse response, Game game) throws IOException;
}
