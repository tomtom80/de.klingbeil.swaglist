package de.klingbeil.swaglist.wishlist.application.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import de.klingbeil.swaglist.wishlist.api.model.WishItemDto;
import de.klingbeil.swaglist.wishlist.application.service.WishListService;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

@RestController
public class WishListController {

  @Resource
  WishListService service;

  @Resource
  ModelMapper modelMapper;

  @GetMapping(value = "/wishlist")
  public ResponseEntity<List<WishItemDto>> findAll() {

    List<WishItem> wishes = service.findAll();
    
    return ResponseEntity.ok().body(convertToWishItemDtoList(wishes));
  }

  @PostMapping(value = "/wishlist")
  public ResponseEntity<WishItemDto> create(@RequestBody WishItemDto resource) {
    WishItem wishItem = convertToWishItem(resource);

    WishItem createdItem = service.persist(wishItem);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(createdItem.getId()).toUri();
    return ResponseEntity.created(location).body(convertToWishItemDto(createdItem));
  }

  @GetMapping(value = "/wishlist/{id}")
  public ResponseEntity<WishItemDto> findOne(@PathVariable String id) {

    WishItem wishItem = service.findOne(id);

    return ResponseEntity.ok().body(convertToWishItemDto(wishItem));
  }

  @PutMapping(value = "/wishlist/{id}")
  public ResponseEntity<WishItemDto> update(@PathVariable(value = "id") String itemId,
      @RequestBody WishItemDto updateItem) {

    WishItem wishItem = service.findOne(itemId);
    WishItem updatedWishItem = updateFromDto(wishItem, updateItem);

    return ResponseEntity.ok().body(convertToWishItemDto(service.persist(updatedWishItem)));
  }

  @DeleteMapping(value = "/wishlist/{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") String itemId) {
    
    WishItem wishItem = service.findOne(itemId);
    this.service.delete(wishItem);
    
    return ResponseEntity.ok().build();
  }

  private WishItem convertToWishItem(WishItemDto resource) {
    return this.modelMapper.map(resource, WishItem.Builder.class).build();
  }

  private WishItemDto convertToWishItemDto(WishItem wish) {
    return modelMapper.map(wish, WishItemDto.class);
  }

  private List<WishItemDto> convertToWishItemDtoList(List<WishItem> wishes) {
    return wishes.stream().map(wish -> convertToWishItemDto(wish)).collect(Collectors.toList());
  }

  private static WishItem updateFromDto(WishItem wishItem, WishItemDto updateItem) {
    return new WishItem.Builder(wishItem).description(updateItem.getDescription())
        .name(updateItem.getName()).build();
  }

}
