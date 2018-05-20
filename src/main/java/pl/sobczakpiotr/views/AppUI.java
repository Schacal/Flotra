package pl.sobczakpiotr.views;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobczakpiotr.authentication.AccessControl;
import pl.sobczakpiotr.authentication.LoginScreen;
import pl.sobczakpiotr.authentication.LoginScreen.LoginListener;
import pl.sobczakpiotr.lang.AppStringConstants;
import pl.sobczakpiotr.model.car.CarDaoImpl;
import pl.sobczakpiotr.model.user.UserDaoImpl;

/**
 * @author Piotr Sobczak, created on 17-03-2018
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("myStyle")
@SpringUI(path = "/*")
public class AppUI extends UI {

  @Autowired
  private AccessControl accessControl;
  private Navigator navigator;

  @Autowired
  private CarDaoImpl carDao;

  @Autowired
  private UserDaoImpl userDao;


  @Override
  protected void init(VaadinRequest request) {
    Responsive.makeResponsive(this);
    getPage().setTitle(AppStringConstants.APP_NAME);
    navigator = new Navigator(this, this);
    navigator.addView(AppStringConstants.LOGIN_VIEW, new LoginScreen(accessControl, new LoginListener() {
      @Override
      public void loginSuccessful() {
        showMainView();
      }
    }));
    navigator.addView(AppStringConstants.MAIN_VIEW, new MainView(carDao, userDao));
  }

  protected void showMainView() {
    addStyleName(ValoTheme.UI_WITH_MENU);
//    setContent(new Label("<h1>Login Sss : </h1>", ContentMode.HTML));
    Navigator navigator = get().getNavigator();
//    String state = navigator.getState();
    navigator.navigateTo(AppStringConstants.MAIN_VIEW);
  }

  public static AppUI get() {
    return (AppUI) UI.getCurrent();
  }
}
