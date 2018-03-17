package pl.sobczakpiotr.views;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import pl.sobczakpiotr.authentication.AccessControl;
import pl.sobczakpiotr.authentication.BasicAccessControl;
import pl.sobczakpiotr.authentication.LoginScreen;
import pl.sobczakpiotr.lang.AppStringConstants;

/**
 * @author Piotr Sobczak, created on 17-03-2018
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("myStyle")
@SpringUI(path = "/*")
public class AppUI extends UI {

  private AccessControl accessControl = new BasicAccessControl();

  @Override
  protected void init(VaadinRequest request) {
    Responsive.makeResponsive(this);
    getPage().setTitle(AppStringConstants.APP_NAME);

    setContent(new LoginScreen(accessControl, (LoginScreen.LoginListener) () -> showMainView()));
  }

  protected void showMainView() {
    addStyleName(ValoTheme.UI_WITH_MENU);
    setContent(new Label("<h1>Login Success!!</h1>", ContentMode.HTML));
    getNavigator().navigateTo(getNavigator().getState());
  }
}
