package de.klingbeil.swaglist.wishlist.application.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.klingbeil.swaglist.wishlist.application.repository.WishListRepository;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

@RestController
@RequestMapping(value = "/wishlist")
public class WishListController {

  @Resource
  WishListRepository repository;

  @RequestMapping( method = RequestMethod.GET)
  public List<WishItem> findAll() {
    return repository.findAll();
  }

}
