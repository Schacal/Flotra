package pl.sobczakpiotr.authentication;

import static pl.sobczakpiotr.lang.AppStringConstants.FORGOT_PASSWORD;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_BUTTON_TEXT;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_FAILED_CAPTION;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_FAILED_DESCRIPTION;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_FORGOT_PASSWORD_HINT;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_PASSWORD;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_PASSWORD_DESCRIPTION;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_USERNAME;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGIN_USERNAME_DESCRIPTION;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGO_FILE_NAME;
import static pl.sobczakpiotr.lang.AppStringConstants.REGISTER_BUTTON_TEXT;
import static pl.sobczakpiotr.lang.Language.ENGLISH;
import static pl.sobczakpiotr.lang.Language.POLISH;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import java.io.Serializable;
import java.util.Optional;
import java.util.ResourceBundle;
import pl.sobczakpiotr.lang.AppStringConstants;
import pl.sobczakpiotr.lang.Language;

/**
 * @author Piotr Sobczak, created on 17-03-2018
 */
@SpringView(name = AppStringConstants.LOGIN_VIEW)
public class LoginScreen extends CssLayout implements View {

  private TextField username;
  private PasswordField password;
  private Button login;
  private Button forgotPassword;
  private Button registerButton;
  private LoginListener loginListener;
  private AccessControl accessControl;
  private final ResourceBundle bundle;

  public LoginScreen(AccessControl accessControl, LoginListener loginListener) {
    this.loginListener = loginListener;
    this.accessControl = accessControl;
    bundle = ResourceBundle.getBundle("bundle", VaadinSession.getCurrent().getLocale());
    buildUI();
    username.focus();
  }

  private void buildUI() {
    addStyleName("login-screen");
    this.addStyleName("backgroundimage");

    Component loginForm = buildLoginForm();
    NativeSelect<Language> languageSelector = getLanguageNativeSelect();
    VerticalLayout verticalLayout = new VerticalLayout();

    String basepath = VaadinService.getCurrent()
        .getBaseDirectory().getAbsolutePath();

// Image as a file resource
    FileResource resource = new FileResource(new File(basepath +
        "/images/" + bundle.getString(LOGO_FILE_NAME)));
    Image image = new Image("", resource);
    image.setWidth(30.0f, Unit.PERCENTAGE);

    verticalLayout.setStyleName("centering-layout");
    verticalLayout.addComponents(languageSelector);
    verticalLayout.setComponentAlignment(languageSelector, Alignment.TOP_RIGHT);
    verticalLayout.addComponent(image);
    verticalLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
    verticalLayout.addComponent(loginForm);
    verticalLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

    this.setSizeFull();

    addComponent(verticalLayout);
  }

  private Component buildLoginForm() {
    FormLayout loginForm = new FormLayout();
    loginForm.addStyleName("login-form");
    loginForm.setSizeUndefined();
    loginForm.setMargin(false);

    loginForm.addComponent(username = new TextField(bundle.getString(LOGIN_USERNAME)));
    username.setWidth(15, Unit.EM);
    username.setDescription(bundle.getString(LOGIN_USERNAME_DESCRIPTION));
    loginForm.addComponent(password = new PasswordField(bundle.getString(LOGIN_PASSWORD)));
    password.setWidth(15, Unit.EM);
    password.setDescription(bundle.getString(LOGIN_PASSWORD_DESCRIPTION));
    CssLayout buttons = new CssLayout();
    buttons.setStyleName("buttons");
    loginForm.addComponent(buttons);

    buttons.addComponent(login = new Button(bundle.getString(LOGIN_BUTTON_TEXT)));
    buttons.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
    buttons.addComponent(registerButton = new Button(bundle.getString(REGISTER_BUTTON_TEXT)));
    registerButton.addClickListener(new ClickListener() {
      @Override
      public void buttonClick(ClickEvent event) {
        Navigator navigator = UI.getCurrent().getNavigator();
        navigator.navigateTo(AppStringConstants.REGISTER_VIEW);
      }
    });
    registerButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
    login.setDisableOnClick(true);
    login.addClickListener(new ClickListener() {
      @Override
      public void buttonClick(ClickEvent event) {
        try {
          LoginScreen.this.login();
        } finally {
          login.setEnabled(true);
        }
      }
    });
    login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    //login.addStyleName("mystyle");
    login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

    buttons.addComponent(forgotPassword = new Button(bundle.getString(FORGOT_PASSWORD)));
    forgotPassword.addClickListener(
        (ClickListener) event -> showNotification(new Notification(bundle.getString(LOGIN_FORGOT_PASSWORD_HINT))));
    forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
    return loginForm;
  }

  private void login() {
//    loginListener.loginSuccessful();
    if (accessControl.signIn(username.getValue(), password.getValue())) {
      loginListener.loginSuccessful();
    } else {
      showNotification(new Notification(bundle.getString(LOGIN_FAILED_CAPTION),
          bundle.getString(LOGIN_FAILED_DESCRIPTION),
          Notification.Type.HUMANIZED_MESSAGE));
      username.focus();
    }
  }

  private void showNotification(Notification notification) {
    notification.setDelayMsec(2000);
    notification.show(Page.getCurrent());
  }

  private NativeSelect<Language> getLanguageNativeSelect() {

    NativeSelect<Language> select =
        new NativeSelect<>();
    select.setItems(ENGLISH, POLISH);
    select.setSelectedItem(Language.convertLocaleToLanguage(VaadinSession.getCurrent().getLocale().toString()).get());
    select.setEmptySelectionAllowed(false);
    select.addSelectionListener((SingleSelectionListener<Language>) event -> {
      Optional<Language> selectedItem = event.getSelectedItem();
      selectedItem.ifPresent(this::setLocale);
      Page.getCurrent().reload();
    });
    return select;
  }

  private void setLocale(Language language) {
    VaadinSession session = VaadinSession.getCurrent();
    switch (language) {
      case ENGLISH:
        session.setLocale(ENGLISH.getLocale());
        break;
      default:
        session.setLocale(POLISH.getLocale());
        break;
    }
  }

  public interface LoginListener extends Serializable {

    void loginSuccessful();
  }
}
