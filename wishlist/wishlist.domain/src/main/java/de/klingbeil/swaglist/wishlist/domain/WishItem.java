package de.klingbeil.swaglist.wishlist.domain;

import de.klingbeil.swaglist.wishlist.utils.Asserts;

public class WishItem {

  private String id;
  private String name;
  private String description;

  private WishItem(Builder builder) {
    id = builder.id;
    name = builder.name;
    description = builder.description;
  }

  public String getId() {
    return id;
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
    private String id;

    public Builder(String id, String name) {
      Asserts.notNull("id", id);
      Asserts.notNull("name", name);

      this.id = id;
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
