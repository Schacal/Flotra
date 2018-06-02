package pl.sobczakpiotr.model.car;

/**
 * @author Piotr Sobczak, created on 01-06-2018
 */
public enum CarBrand {
  Alfa_Romeo, Aston_Martin, Audi, Bentley, Benz, BMW, Bugatti, Cadillac, Chevrolet, Chrysler, Citroën, Corvette, DAF, Dacia, Daewoo, Daihatsu, Datsun, De_Lorean, Dino, Dodge, Farboud, Ferrari, Fiat, Ford, Honda, Hummer, Hyundai, Jaguar, Jeep, KIA, Koenigsegg, Lada, Lamborghini, Lancia, Land_Rover, Lexus, Ligier, Lincoln, Lotus, Martini, Maserati, Maybach, Mazda, McLaren, Mercedes_Benz, Mini, Mitsubishi, Nissan, Noble, Opel, Peugeot, Pontiac, Porsche, Renault, Rolls_Royce, Saab, Seat, Škoda, Smart, Spyker, Subaru, Suzuki, Toyota, Vauxhall, Volkswagen, Volvo, Other;

  public static CarBrand findBy(String brand) {
    CarBrand[] values = CarBrand.values();
    for (CarBrand value : values) {
      if (value.name().equals(brand)) {
        return value;
      }
    }

    return CarBrand.Other;
  }

}


