package de.klingbeil.swaglist.wishlist.application.repository;

import java.util.List;
import de.klingbeil.swaglist.wishlist.domain.WishItem;


public interface WishListService {

  List<WishItem> findAll();

  WishItem findOne(String id);

  WishItem create(WishItem wishItem);

}
