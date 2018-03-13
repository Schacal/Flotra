package pl.sobczakpiotr.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Piotr Sobczak, created on 13-03-2018
 */
@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot, time is " + System.currentTimeMillis() + " long :)";
  }

}
