package de.klingbeil.swaglist.wishlist.utils;

public class Asserts {

  private Asserts() {}

  public static void notNull(String paramName, Object paramValue) {
    if (paramValue == null) {
      throw new IllegalArgumentException("Param '" + paramName + "' must not be null");
    }
  }

}
