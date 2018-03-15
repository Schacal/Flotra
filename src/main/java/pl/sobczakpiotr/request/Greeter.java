package pl.sobczakpiotr.request;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * @author Piotr Sobczak, created on 15-03-2018
 */
@SpringComponent
@UIScope
public class Greeter {

  public String sayHello() {
    return "Hello from bean " + toString();
  }
}
