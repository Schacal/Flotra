package pl.sobczakpiotr.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobczakpiotr.request.Greeter;

/**
 * @author Piotr Sobczak, created on 15-03-2018
 */
@UIScope
@SpringView(name = UIScopedView.VIEW_NAME)
public class UIScopedView extends HorizontalLayout implements View {

  public static final String VIEW_NAME = "ui";

  @Autowired
  private Greeter greeter;

  @PostConstruct
  void init() {
    addComponent(new Label("This is a UI scoped view. Greeter says: " + greeter.sayHello()));
  }

  @Override
  public void enter(ViewChangeEvent event) {

  }
}
