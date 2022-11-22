package com.server.RoadToInerview.JWTTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;

import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebIntegrationTest {
    @LocalServerPort
    int port;

    public URI uri(String path){
        try {
            URI uri = new URI(format("http://localhost:%d%s", port, path));
            return uri;
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
