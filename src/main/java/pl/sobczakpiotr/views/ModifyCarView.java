package pl.sobczakpiotr.views;

import static pl.sobczakpiotr.lang.AppStringConstants.ADD_CAR_BRAND_LABEL;
import static pl.sobczakpiotr.lang.AppStringConstants.ADD_CAR_LICENCE_PLATE_LABEL;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_ADDITIONAL_EQUIPMENT;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_COLOR;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_DOORS_NUMBER;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_ENGINE;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_ENGINE_POWER;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_FUEL_TYPE;
import static pl.sobczakpiotr.lang.AppStringConstants.CAR_MILEAGE;
import static pl.sobczakpiotr.lang.AppStringConstants.INSURANCE_END_DATE;
import static pl.sobczakpiotr.lang.AppStringConstants.INSURANCE_START_DATE;
import static pl.sobczakpiotr.lang.AppStringConstants.LOGO_FILE_NAME;
import static pl.sobczakpiotr.lang.AppStringConstants.MODIFY_CAR_LABEL;
import static pl.sobczakpiotr.lang.AppStringConstants.TECHNICAL_EXAMINATION_END_DATE;
import static pl.sobczakpiotr.lang.Language.ENGLISH;
import static pl.sobczakpiotr.lang.Language.POLISH;

import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import java.security.InvalidParameterException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import pl.sobczakpiotr.authentication.CurrentUser;
import pl.sobczakpiotr.lang.AppStringConstants;
import pl.sobczakpiotr.lang.Language;
import pl.sobczakpiotr.model.car.CarBrand;
import pl.sobczakpiotr.model.car.CarDaoImpl;
import pl.sobczakpiotr.model.car.CarEntity;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;
import pl.sobczakpiotr.model.user.UserDaoImpl;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 02-06-2018
 */
public class ModifyCarView extends AbsoluteLayout implements View {

  private CarDaoImpl carDao;
  private UserDaoImpl userDao;
  private final ResourceBundle bundle;

  public ModifyCarView(CarDaoImpl carDao, UserDaoImpl userDao) {

    this.carDao = carDao;
    this.userDao = userDao;
    this.bundle = ResourceBundle.getBundle("bundle", VaadinSession.getCurrent().getLocale());
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
    Label label = new Label(bundle.getString(MODIFY_CAR_LABEL));
    label.setStyleName(ValoTheme.LABEL_HUGE);

    addComponent(languageNativeSelect, "top: 1%; right: 1%");
    addComponent(image, "top: 1%; right: 34%");
    addComponent(label, "top: 28%; right: 48%");
    String parameters = event.getParameters();
    createTextFields(Integer.parseInt(parameters));
  }


  private void createTextFields(int carId) {
    Optional<CarEntity> car = carDao.findCar(carId);
    if (car.isPresent()) {
      CarEntity oldCarEntity = car.get();
      CardetailsEntity oldCarDetailsEntity = oldCarEntity.getCarDetailsEntity();
      Label brandLabel = new Label(bundle.getString(ADD_CAR_BRAND_LABEL));
      brandLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(brandLabel, "top: 35%; left: 20%");
      NativeSelect<CarBrand> carSelector = new NativeSelect<>("", Arrays.asList(CarBrand.values()));
      carSelector.setEmptySelectionAllowed(false);
      carSelector.setStyleName(ValoTheme.LABEL_H3);
      carSelector.setWidth(186.0f, Unit.PIXELS);
      carSelector.setSelectedItem(CarBrand.findBy(oldCarDetailsEntity.getManufacturer()));
      addComponent(carSelector, "top: 38%; left: 24%");

      Label modelLabel = new Label("Model");
      modelLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(modelLabel, "top: 40%; left: 20%");
      TextField modelTextField = new TextField();
      modelTextField.setStyleName(ValoTheme.LABEL_H3);
      modelTextField.setValue(oldCarDetailsEntity.getModel());
      addComponent(modelTextField, "top: 43%; left: 24%");

      Label licensePlateLabel = new Label(bundle.getString(ADD_CAR_LICENCE_PLATE_LABEL));
      licensePlateLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(licensePlateLabel, "top: 45%; left: 10%");
      TextField licensePlateTextField = new TextField();
      licensePlateTextField.setStyleName(ValoTheme.LABEL_H3);
      licensePlateTextField.setValue(oldCarEntity.getLicensePlateNumber());
      addComponent(licensePlateTextField, "top: 48%; left: 24%");

      Label vinLabel = new Label("VIN");
      vinLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(vinLabel, "top: 50%; left: 21%");
      TextField vinField = new TextField();
      vinField.setStyleName(ValoTheme.LABEL_H3);
      vinField.setValue(String.valueOf(oldCarEntity.getVinNumber()));
      addComponent(vinField, "top: 53%; left: 24%");

      Label insuranceStartDateLabel = new Label(bundle.getString(INSURANCE_START_DATE));
      insuranceStartDateLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(insuranceStartDateLabel, "top: 55%; left: 12%");
      DateTimeField insuranceStartDateTextField = new DateTimeField();
      insuranceStartDateTextField.setValue(LocalDateTime.now());
      insuranceStartDateTextField.setResolution(DateTimeResolution.DAY);
      insuranceStartDateTextField.setValue(convertToLocalDateTimeViaMilisecond(oldCarEntity.getInsuranceStartDate()));
      addComponent(insuranceStartDateTextField, "top: 58%; left: 24%");

      Label insuranceEndDateLabel = new Label(bundle.getString(INSURANCE_END_DATE));
      insuranceEndDateLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(insuranceEndDateLabel, "top: 60%; left: 9%");
      DateTimeField insuranceEndDateTextField = new DateTimeField();
      insuranceEndDateTextField.setValue(LocalDateTime.now());
      insuranceEndDateTextField.setResolution(DateTimeResolution.DAY);
      insuranceEndDateTextField.setValue(convertToLocalDateTimeViaMilisecond(oldCarEntity.getInsuranceEndDate()));
      addComponent(insuranceEndDateTextField, "top: 63%; left: 24%");

      Label technicalEndDateLabel = new Label(bundle.getString(TECHNICAL_EXAMINATION_END_DATE));
      technicalEndDateLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(technicalEndDateLabel, "top: 65%; left: 9%");
      DateTimeField technicalEndDateTextField = new DateTimeField();
      technicalEndDateTextField.setValue(LocalDateTime.now());
      technicalEndDateTextField.setResolution(DateTimeResolution.DAY);
      technicalEndDateTextField
          .setValue(convertToLocalDateTimeViaMilisecond(oldCarEntity.getTechnicalExaminationEndDate()));
      addComponent(technicalEndDateTextField, "top: 68%; left: 24%");

      //-----------

      Label engineLabel = new Label(bundle.getString(CAR_ENGINE));
      engineLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(engineLabel, "top: 35%; left: 45%");
      TextField engineTextField = new TextField();
      engineTextField.setStyleName(ValoTheme.LABEL_H3);
      engineTextField.setValue(oldCarDetailsEntity.getEngine());
      addComponent(engineTextField, "top: 38%; left: 49%");

      Label fuelLabel = new Label(bundle.getString(CAR_FUEL_TYPE));
      fuelLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(fuelLabel, "top: 40%; left: 43%");
      NativeSelect<String> fuelSelector = new NativeSelect<>("",
          Arrays.asList("petrol", "diesel", "hybrid", "LPG", "electric"));
      fuelSelector.setStyleName(ValoTheme.LABEL_H3);
      fuelSelector.setEmptySelectionAllowed(false);
      fuelSelector.setWidth(186.0f, Unit.PIXELS);
      fuelSelector.setSelectedItem(oldCarDetailsEntity.getFuelType());
      addComponent(fuelSelector, "top: 43%; left: 49%");

      Label colorLabel = new Label(bundle.getString(CAR_COLOR));
      colorLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(colorLabel, "top: 45%; left: 45%");
      TextField colorTextField = new TextField();
      colorTextField.setStyleName(ValoTheme.LABEL_H3);
      colorTextField.setValue(oldCarDetailsEntity.getColor());
      addComponent(colorTextField, "top: 48%; left: 49%");

      Label enginePowerLabel = new Label(bundle.getString(CAR_ENGINE_POWER));
      enginePowerLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(enginePowerLabel, "top: 50%; left: 40%");
      TextField enginePowerTextField = new TextField();
      enginePowerTextField.setStyleName(ValoTheme.LABEL_H3);
      enginePowerTextField.setValue(String.valueOf(oldCarDetailsEntity.getEnginePower()));
      addComponent(enginePowerTextField, "top: 53%; left: 49%");

      Label mileageLabel = new Label(bundle.getString(CAR_MILEAGE));
      mileageLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(mileageLabel, "top: 55%; left: 41%");
      TextField mileageTextField = new TextField();
      mileageTextField.setStyleName(ValoTheme.LABEL_H3);
      mileageTextField.setValue(String.valueOf(oldCarDetailsEntity.getMileage()));
      addComponent(mileageTextField, "top: 58%; left: 49%");

      Label doorsNumberLabel = new Label(bundle.getString(CAR_DOORS_NUMBER));
      doorsNumberLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(doorsNumberLabel, "top: 60%; left: 42%");
      TextField doorsNumberTextField = new TextField();
      doorsNumberTextField.setStyleName(ValoTheme.LABEL_H3);
      doorsNumberTextField.setValue(String.valueOf(oldCarDetailsEntity.getDoorsNumber()));
      addComponent(doorsNumberTextField, "top: 63%; left: 49%");

      Label equipmentLabel = new Label(bundle.getString(CAR_ADDITIONAL_EQUIPMENT));
      equipmentLabel.setStyleName(ValoTheme.LABEL_H3);
      addComponent(equipmentLabel, "top: 35%; left: 67%");
      TextArea equipmentTextField = new TextArea();
      equipmentTextField.setStyleName(ValoTheme.LABEL_H3);
      equipmentTextField.setHeight(300.0f, Unit.PIXELS);
      equipmentTextField.setWidth(300.0f, Unit.PIXELS);
      equipmentTextField.setValue(oldCarDetailsEntity.getEquipment());
      addComponent(equipmentTextField, "top: 38%; left: 79%");

      Button addNewCarButton = new Button(bundle.getString(MODIFY_CAR_LABEL));
      addNewCarButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
      addNewCarButton.setWidth(200.0f, Unit.PIXELS);
      addNewCarButton.addClickListener(new ClickListener() {
        @Override
        public void buttonClick(ClickEvent event) {
          String currentUser = CurrentUser.get();
          Optional<UserEntity> userDb = userDao.findUserByName(currentUser);
          if (!userDb.isPresent()) {
            throw new InvalidParameterException();
          }

          oldCarDetailsEntity.setManufacturer(carSelector.getValue().toString());
          oldCarDetailsEntity.setModel(modelTextField.getValue());
          oldCarDetailsEntity.setEngine(engineTextField.getValue());
          oldCarDetailsEntity.setFuelType(fuelSelector.getValue());
          oldCarDetailsEntity.setColor(colorTextField.getValue());
          oldCarDetailsEntity.setEnginePower(Integer.valueOf(enginePowerTextField.getValue()));
          oldCarDetailsEntity.setMileage(Integer.valueOf(mileageTextField.getValue()));
          oldCarDetailsEntity.setDoorsNumber(Integer.valueOf(doorsNumberTextField.getValue()));
          oldCarDetailsEntity.setEquipment(equipmentTextField.getValue());

//          CarEntity carEntity = new CarEntity();
          oldCarEntity.setUserByUserId(userDb.get());
          oldCarEntity.setHolders(new ArrayList<>());
          oldCarDetailsEntity.setCarByCarId(oldCarEntity);
          oldCarEntity.setCarDetailsEntity(oldCarDetailsEntity);
          oldCarEntity.setVinNumber(Long.valueOf(vinField.getValue()));
          oldCarEntity.setInsuranceStartDate(
              new Date(insuranceStartDateTextField.getValue().toInstant(ZoneOffset.UTC).toEpochMilli()));
          oldCarEntity.setInsuranceEndDate(
              new Date(insuranceStartDateTextField.getValue().toInstant(ZoneOffset.UTC).toEpochMilli()));
          oldCarEntity.setLicensePlateNumber(licensePlateTextField.getValue());
          oldCarEntity.setTechnicalExaminationEndDate(
              new Date(technicalEndDateTextField.getValue().toInstant(ZoneOffset.UTC).toEpochMilli()));

          carDao.updateCar(oldCarEntity);

          Navigator navigator = UI.getCurrent().getNavigator();
          navigator.navigateTo(AppStringConstants.MAIN_VIEW);
        }
      });

      addComponent(addNewCarButton, "top: 78%; left: 44%");
    }

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

  public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }
}
