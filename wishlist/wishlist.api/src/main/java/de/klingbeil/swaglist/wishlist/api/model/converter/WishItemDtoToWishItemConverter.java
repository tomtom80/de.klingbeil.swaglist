package de.klingbeil.swaglist.wishlist.api.model.converter;

import org.modelmapper.PropertyMap;
import de.klingbeil.swaglist.wishlist.api.model.WishItemDto;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishItemDtoToWishItemConverter extends PropertyMap<WishItemDto, WishItem.Builder> {
  @Override
  protected void configure() {
    map().id(source.getId());
    map().name(source.getName());
    map().description(source.getDescription());
  }
}
