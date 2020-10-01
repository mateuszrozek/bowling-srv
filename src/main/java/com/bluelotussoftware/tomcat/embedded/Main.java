/*
 * Copyright 2017-2018 Blue Lotus Software, LLC.
 * Copyright 2017-2018 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bluelotussoftware.tomcat.embedded;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;

/**
 * An Example Embedded Apache Tomcat with an anonymous inner class
 * {@link HttpServlet}.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0.0
 */
public class Main {

    /**
     * Main method.
     *
     * @param args command line arguments passed to the application. Currently
     * unused.
     * @throws LifecycleException If a life cycle exception occurs.
     * @throws InterruptedException If the application is interrupted while
     * waiting for requests.
     * @throws ServletException If the servlet handling the response has an
     * exception.
     */
    public static void main(String[] args)
            throws LifecycleException, InterruptedException, ServletException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context gameCtx = tomcat.addContext("", new File(".").getAbsolutePath());
        Tomcat.addServlet(gameCtx, "game", new GameServlet());
        gameCtx.addServletMappingDecoded("/*", "game");

        tomcat.start();
        tomcat.getServer().await();
    }

}
