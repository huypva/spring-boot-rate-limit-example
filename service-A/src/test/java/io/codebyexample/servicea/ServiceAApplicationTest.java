package io.codebyexample.servicea;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author huypva
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ServiceAApplicationTest {

  @LocalServerPort
  private int serverPort;

  @Test
  public void test() throws InterruptedException {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:" + serverPort + "/greet";

    for (int i = 1; i <= 20; i++) {
      long startTime = System.currentTimeMillis();

      ResponseEntity<String> response = null;
      try {
        response = restTemplate.getForEntity(url + "/" + (i * 1000), String.class);
        long processTime = System.currentTimeMillis() - startTime;
        if (processTime < 100) {
          Thread.sleep(100 - processTime);
        }
        log.info("Response [" + i + "] : " + response.getBody());
      } catch (HttpClientErrorException ex) {
        log.error("Response [" + i + "] : " + ex.getStatusCode());
        Thread.sleep(200);
      }
    }

  }
}