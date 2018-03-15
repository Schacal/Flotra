package pl.sobczakpiotr.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;

/**
 * @author Piotr Sobczak, created on 15-03-2018
 */
@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
  public static final String VIEW_NAME = "";


  @PostConstruct
  void init(){
    addComponent(new Label("This is default view"));
  }

  @Override
  public void enter(ViewChangeEvent event) {
    // This view is constructed in the init() method()
  }
}
