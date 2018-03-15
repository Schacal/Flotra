package pl.sobczakpiotr.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import pl.sobczakpiotr.customer.Customer;
import pl.sobczakpiotr.customer.CustomerService;

/**
 * @author Piotr Sobczak, created on 15-03-2018
 */
@Theme("valo")
@SpringUI(path = "/")
@SpringView(name = AuthPage.LOGGING_PAGE)
public class AuthPage extends UI implements View {

  public static final String LOGGING_PAGE = "auth";
  private CustomerService service = CustomerService.getInstance();
  private Grid<Customer> grid = new Grid<>(Customer.class);

  @Override
  protected void init(VaadinRequest request) {
  final VerticalLayout layout = new VerticalLayout();
  layout.addComponents(grid);
    List<Customer> all = service.findAll();
    grid.setItems(all);

    setContent(layout);


  }


  @Override
  public void enter(ViewChangeEvent event) {

  }
}