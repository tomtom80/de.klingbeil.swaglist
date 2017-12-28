package de.klingbeil.swaglist.wishlist.persistence.model.converter;

import org.modelmapper.PropertyMap;
import de.klingbeil.swaglist.wishlist.domain.WishItem;
import de.klingbeil.swaglist.wishlist.persistence.model.WishItemModel;

public class WishItemModelToWishItemConverter extends PropertyMap<WishItemModel, WishItem.Builder> {
  @Override
  protected void configure() {
    map().id(source.getId());
    map().name(source.getName());
    map().description(source.getDescription());
  }
}
