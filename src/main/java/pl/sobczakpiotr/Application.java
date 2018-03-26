package pl.sobczakpiotr.request;

import com.vaadin.server.VaadinSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.sobczakpiotr.lang.Language;

/**
 * @author Piotr Sobczak, created on 13-03-2018
 */

@ComponentScan("pl.sobczakpiotr")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
