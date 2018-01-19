package de.klingbeil.swaglist.wishlist.application.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import de.klingbeil.swaglist.wishlist.domain.WishItem;
import de.klingbeil.swaglist.wishlist.persistence.model.WishItemModel;
import de.klingbeil.swaglist.wishlist.persistence.repository.WishItemRepository;

@Service
public class WishListServiceImpl implements WishListService {

  @Resource
  WishItemRepository wishItemRepository;
  @Resource
  ModelMapper modelMapper;


  @Override
  public List<WishItem> findAll() {
    List<WishItemModel> wishes = wishItemRepository.findAll();
    return convertModelsToItems(wishes);
  }

  @Override
  public WishItem findOne(String id) {
    WishItemModel wish = wishItemRepository.findOne(id);
    return convertModelToItem(wish);
  }

  @Override
  public WishItem persist(WishItem wishItem) {
    WishItemModel itemModel = convertItemToModel(wishItem);
    WishItemModel wish = wishItemRepository.save(itemModel);
    return convertModelToItem(wish);
  }

  @Override
  public void delete(WishItem wishItem) {
    WishItemModel itemModel = convertItemToModel(wishItem);
    wishItemRepository.delete(itemModel);
  }

  private WishItemModel convertItemToModel(WishItem wishItem) {
    return this.modelMapper.map(wishItem, WishItemModel.class);
  }

  private List<WishItem> convertModelsToItems(List<WishItemModel> wishes) {
    return wishes.stream().map(wishItemModel -> convertModelToItem(wishItemModel))
        .collect(Collectors.toList());
  }

  private WishItem convertModelToItem(WishItemModel wishItemModel) {
    return modelMapper.map(wishItemModel, WishItem.Builder.class).build();
  }

}
