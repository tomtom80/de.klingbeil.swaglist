package de.klingbeil.swaglist.wishlist.domain;

import de.klingbeil.swaglist.wishlist.utils.Asserts;

public class WishItem {

  private String name;
  private String description;

  private WishItem(Builder builder) {
    name = builder.name;
    description = builder.description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public static class Builder {

    private String name;
    private String description;

    public Builder(String name) {
      Asserts.notNull("name", name);

      this.name = name;
    }

    public WishItem build() {
      return new WishItem(this);
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }
  }

}
