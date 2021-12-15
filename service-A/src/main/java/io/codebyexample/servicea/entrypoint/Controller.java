package io.codebyexample.servicea.entrypoint;

import io.codebyexample.servicea.util.GsonUtils;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huypva
 */
@Setter
@Slf4j
@RestController
public class Controller {

  @GetMapping("/greet/{id}")
  @RateLimiter(name = "serviceA_greet", fallbackMethod = "greetFallBack")
  public ResponseEntity greet(@PathVariable(name = "id") int id) {
    log.info("ServiceA.greet");
    Greeting greeting = new Greeting(id, "Hello ServiceA");

    return ResponseEntity.ok()
        .body(GsonUtils.toJson(greeting));
  }

  public ResponseEntity greetFallBack(int id, Throwable t) {
    log.error("Rate limit applied");
    return ResponseEntity
        .status(HttpStatus.TOO_MANY_REQUESTS)
        .build();
  }

}
