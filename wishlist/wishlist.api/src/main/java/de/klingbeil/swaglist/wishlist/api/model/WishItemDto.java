package de.klingbeil.swaglist.wishlist.api.model;

public class WishItemDto {

  private String id;
  private String name;
  private String description;


  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
