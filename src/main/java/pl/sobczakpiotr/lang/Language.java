package pl.sobczakpiotr.lang;

import java.util.Locale;
import java.util.Locale.Builder;
import java.util.Optional;

/**
 * @author Piotr Sobczak, created on 17-03-2018
 */
public enum Language {
  ENGLISH(Locale.US, "English"),
  POLISH(new Builder().setLanguage("pl").setRegion("PL").build(), "Polish");

  private final Locale locale;
  private final String name;


  Language(Locale locale, String name) {
    this.locale = locale;
    this.name = name;
  }

  public Locale getLocale() {
    return locale;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  public static Optional<Language> convertLocaleToLanguage(String locale) {
    if (ENGLISH.locale.toString().equalsIgnoreCase(locale)) {
      return Optional.of(ENGLISH);
    } else if (POLISH.locale.toString().equalsIgnoreCase(locale)) {
      return Optional.of(POLISH);
    } else {
      return Optional.empty();
    }
  }
}
