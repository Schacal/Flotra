package pl.sobczakpiotr.views;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobczakpiotr.authentication.AccessControl;
import pl.sobczakpiotr.authentication.BasicAccessControl;
import pl.sobczakpiotr.authentication.LoginScreen;
import pl.sobczakpiotr.authentication.LoginScreen.LoginListener;
import pl.sobczakpiotr.lang.AppStringConstants;
import pl.sobczakpiotr.model.UserDaoImpl;

/**
 * @author Piotr Sobczak, created on 17-03-2018
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("myStyle")
@SpringUI(path = "/*")
public class AppUI extends UI {

  private AccessControl accessControl = new BasicAccessControl();

  @Autowired
  private UserDaoImpl userDao;

  @Override
  protected void init(VaadinRequest request) {
    Responsive.makeResponsive(this);
    getPage().setTitle(AppStringConstants.APP_NAME);

    setContent(new LoginScreen(accessControl, new LoginListener() {
      @Override
      public void loginSuccessful() {
        showMainView();
      }
    }));
  }

  protected void showMainView() {
    addStyleName(ValoTheme.UI_WITH_MENU);
    List all = userDao.getAllUsers();
    setContent(new Label("<h1>Login Success!! List of all users : "+all.toString()+"</h1>", ContentMode.HTML));
    Navigator navigator = get().getNavigator();
    Navigator navigator1 = get().getNavigator();
    String state = navigator1.getState();
    navigator.navigateTo(state);
  }

  public static AppUI get() {
    return (AppUI) UI.getCurrent();
  }
}
