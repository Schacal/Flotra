package pl.sobczakpiotr.views;

import static pl.sobczakpiotr.lang.AppStringConstants.ADD_CAR_BUTTON_TEXT;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_ENGINE;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_ENGINE_POWER;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_FUEL_TYPE;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_MANUFACTURER;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_MILEAGE;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_MODEL;
import static pl.sobczakpiotr.lang.AppStringConstants.CAr_LICENSE_PLATE;
import static pl.sobczakpiotr.lang.AppStringConstants.DASHBOARD;
import static pl.sobczakpiotr.lang.AppStringConstants.DELETE_CAR_BUTTON_TEXT;
import static pl.sobczakpiotr.lang.AppStringConstants.INSURANCE_END_DATE;
import static pl.sobczakpiotr.lang.AppStringConstants.INSURANCE_START_DATE;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGGED_USER_LABEL;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGOUT_BUTTON;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGO_FILE_NAME;
import static pl.sobczakpiotr.lang.AppStringConstants.MAIN_VIEW;
import static pl.sobczakpiotr.lang.AppStringConstants.MODIFY_CAR_BUTTON_TEXT;
import static pl.sobczakpiotr.lang.AppStringConstants.TECHNICAL_EXAMINATION_END_DATE;
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
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import pl.sobczakpiotr.authentication.CurrentUser;
import pl.sobczakpiotr.lang.Language;
import pl.sobczakpiotr.model.car.CarDaoImpl;
import pl.sobczakpiotr.model.car.CarEntity;
import pl.sobczakpiotr.model.user.UserDaoImpl;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 23-04-2018
 */
@SpringView(name = MAIN_VIEW)
public class MainView extends AbsoluteLayout implements View {

  private CarDaoImpl carDao;
  private UserDaoImpl userDao;


  private ResourceBundle bundle;

  public MainView(CarDaoImpl carDao, UserDaoImpl userDao) {
    this.carDao = carDao;
    this.userDao = userDao;
    bundle = ResourceBundle.getBundle("bundle", VaadinSession.getCurrent().getLocale());
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

  @Override
  public void enter(ViewChangeEvent event) {
    String userName = CurrentUser.get();

    setResponsive(true);
    this.addStyleName("backgroundimage");
    String basepath = VaadinService.getCurrent()
        .getBaseDirectory().getAbsolutePath();
    FileResource resource = new FileResource(new File(basepath +
        "/images/" + bundle.getString(LOGO_FILE_NAME)));
    Image image = new Image("", resource);
    image.setWidth(300.0f, Unit.PIXELS);
    image.setHeight(150.0f, Unit.PIXELS);
    addComponent(image, "top: 1%; left: 1%");

    Button logInOut = new Button(bundle.getString(LOGOUT_BUTTON),
        (ClickListener) e -> {
          CurrentUser.set(null);
          UI.getCurrent().getNavigator().navigateTo("");
        });
    NativeSelect<Language> languageNativeSelect = getLanguageNativeSelect();
    languageNativeSelect.setWidth(100.0f, Unit.PIXELS);
    logInOut.setWidth(130.0f, Unit.PIXELS);
    logInOut.setStyleName(ValoTheme.BUTTON_PRIMARY);
    addComponent(logInOut, "top: 1%; left:84%");
    addComponent(languageNativeSelect, "top: 1%; right: 1%");

    Button dashboardButton = new Button(bundle.getString(DASHBOARD));
    dashboardButton.setWidth(170.0f, Unit.PIXELS);
    Button addCarButton = new Button(bundle.getString(ADD_CAR_BUTTON_TEXT));
    addCarButton.setWidth(170.0f, Unit.PIXELS);
    Button modifyCarButton = new Button(bundle.getString(MODIFY_CAR_BUTTON_TEXT));
    modifyCarButton.setWidth(170.0f, Unit.PIXELS);
    Button deleteCarButton = new Button(bundle.getString(DELETE_CAR_BUTTON_TEXT));
    deleteCarButton.setWidth(170.0f, Unit.PIXELS);
    addCarButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
    deleteCarButton.setStyleName(ValoTheme.BUTTON_DANGER);

    Grid<CarEntity> grid = preapreGridForCars(userName);

    addComponent(dashboardButton, "top: 15%; left:1%");
    addComponent(addCarButton, "top: 21%; left:1%");
    addComponent(modifyCarButton, "top: 27%; left:1%");
    addComponent(deleteCarButton, "top: 33%; left:1%");
    addComponent(grid, "top: 15%; left:12%");
    String currentUSer = CurrentUser.get();
    Label labelLoggedUser = new Label(bundle.getString(LOGGED_USER_LABEL) + currentUSer);
    labelLoggedUser.setStyleName(ValoTheme.LABEL_H2);
    addComponent(labelLoggedUser, "top: 5%; left:80%");
    grid.setWidth(95, Unit.PERCENTAGE);


  }

  private Grid<CarEntity> preapreGridForCars(String user) {
    Grid<CarEntity> grid = new Grid<>();

    grid.addColumn(car -> car.getCarDetailsEntity().getManufacturer()).setCaption(bundle.getString(CAR_MANUFACTURER));
    grid.addColumn(car -> car.getCarDetailsEntity().getModel()).setCaption(bundle.getString(CAR_MODEL));
    grid.addColumn(car -> car.getCarDetailsEntity().getEngine()).setCaption(bundle.getString(CAR_ENGINE));
    grid.addColumn(car -> car.getCarDetailsEntity().getEnginePower()).setCaption(bundle.getString(CAR_ENGINE_POWER));
    grid.addColumn(car -> car.getCarDetailsEntity().getFuelType()).setCaption(bundle.getString(CAR_FUEL_TYPE));
    grid.addColumn(car -> car.getCarDetailsEntity().getMileage()).setCaption(bundle.getString(CAR_MILEAGE));
    grid.addColumn(CarEntity::getLicensePlateNumber).setCaption(bundle.getString(CAr_LICENSE_PLATE));

    grid.addColumn(CarEntity::getInsuranceStartDate).setCaption(bundle.getString(INSURANCE_START_DATE));
    grid.addColumn(CarEntity::getInsuranceEndDate).setCaption(bundle.getString(INSURANCE_END_DATE));
    grid.addColumn(CarEntity::getTechnicalExaminationEndDate)
        .setCaption(bundle.getString(TECHNICAL_EXAMINATION_END_DATE));
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(user);
    List<CarEntity> allCarsForUser = carDao.getAllCarsForUser(userEntity);
    grid.setItems(allCarsForUser);
    grid.setHeightByRows(allCarsForUser.size());

    return grid;
  }

}
