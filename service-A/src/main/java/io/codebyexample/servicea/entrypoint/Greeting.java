package io.codebyexample.servicea.entrypoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author huypva
 */
@Setter
@Getter
@AllArgsConstructor
public class Greeting {

  private int id;
  private String message;

}
