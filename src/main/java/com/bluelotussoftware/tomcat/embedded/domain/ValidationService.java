package com.bluelotussoftware.tomcat.embedded.domain;

import javax.servlet.http.HttpServletResponse;

public interface ValidationService {
    boolean validateRequest(String pins, HttpServletResponse response, Game game) throws Exception;
}
