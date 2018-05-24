package pl.sobczakpiotr.views;

import static pl.sobczakpiotr.lang.AppStringConstants.E_MAIL;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGO_FILE_NAME;
import static pl.sobczakpiotr.lang.AppStringConstants.PASSWORD;
import static pl.sobczakpiotr.lang.AppStringConstants.REGISTER_BUTTON_TEXT;
import static pl.sobczakpiotr.lang.AppStringConstants.REGISTER_LABEL;
import static pl.sobczakpiotr.lang.AppStringConstants.USER_NAME;
import static pl.sobczakpiotr.lang.Language.ENGLISH;
import static pl.sobczakpiotr.lang.Language.POLISH;

import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobczakpiotr.lang.AppStringConstants;
import pl.sobczakpiotr.lang.Language;
import pl.sobczakpiotr.model.user.UserDaoImpl;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 20-05-2018
 */
@SpringView(name = AppStringConstants.REGISTER_VIEW)
public class RegisterView extends AbsoluteLayout implements View {

  private final ResourceBundle bundle;

  @Autowired
  private UserDaoImpl userDao;

  public RegisterView(UserDaoImpl userDao) {
    this.userDao = userDao;
    bundle = ResourceBundle.getBundle("bundle", VaadinSession.getCurrent().getLocale());
  }

  @Override
  public void enter(ViewChangeEvent event) {
    NativeSelect<Language> languageNativeSelect = getLanguageNativeSelect();

    String basepath = VaadinService.getCurrent()
        .getBaseDirectory().getAbsolutePath();
    FileResource resource = new FileResource(new File(basepath +
        "/images/" + bundle.getString(LOGO_FILE_NAME)));
    Image image = new Image("", resource);
    this.setSizeFull();
    this.addStyleName("backgroundimage");
//    addComponent(logInOut, "top: 1%; left:1%");
    image.setWidth(600.0f, Unit.PIXELS);
    image.setHeight(300.0f, Unit.PIXELS);

    addComponent(languageNativeSelect, "top: 1%; right: 1%");
    addComponent(image, "top: 10%; right: 34%");

    buildRegisterForm();
  }


  private void buildRegisterForm() {
    TextField userNameField = new TextField();
    TextField emailField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button registerButton = new Button(bundle.getString(REGISTER_BUTTON_TEXT));
    registerButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
    registerButton.setWidth(150.0f, Unit.PIXELS);
    Label registerText = new Label(bundle.getString(REGISTER_LABEL));
    registerText.addStyleName(ValoTheme.LABEL_H2);
    addComponent(registerText, "top: 35%; right: 40%");
    addComponent(new Label(bundle.getString(USER_NAME)), "top: 50%; right: 55%");
    addComponent(userNameField, "top: 50%; right: 45%");
    addComponent(new Label(bundle.getString(PASSWORD)), "top: 55%; right: 55%");
    addComponent(passwordField, "top: 55%; right: 45%");
    addComponent(new Label(bundle.getString(E_MAIL)), "top: 60%; right: 55%");
    addComponent(emailField, "top: 60%; right: 45%");

    registerButton.addClickListener(new ClickListener() {
      @Override
      public void buttonClick(ClickEvent event) {
        if (!emailField.isEmpty() && !userNameField.isEmpty() && !passwordField.isEmpty()) {
          UserEntity userEntity = new UserEntity();
          userEntity.setUserName(userNameField.getValue());
          userEntity.setPassword(passwordField.getValue());
          userEntity.setEmail(emailField.getValue());

          userDao.createUser(userEntity);
        } else {
          userNameField.clear();
          passwordField.clear();
          emailField.clear();
          UI.getCurrent().getNavigator().navigateTo(AppStringConstants.REGISTER_VIEW);
        }
      }
    });

    addComponent(registerButton, "top: 75%; right: 45%");
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
}
