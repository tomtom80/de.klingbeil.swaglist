package de.klingbeil.swaglist.wishlist.application.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import de.klingbeil.swaglist.wishlist.api.model.WishItemDto;
import de.klingbeil.swaglist.wishlist.application.repository.WishListService;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

@RestController
public class WishListController {

  @Resource
  WishListService service;
  
  @Resource
  ModelMapper modelMapper;
  
  @GetMapping(value = "/wishlist")
  public List<WishItemDto> findAll() {
    
    List<WishItem> wishes = service.findAll();
   
    return wishes.stream()
    .map(wish -> convertToWishItemDto(wish) )
    .collect(Collectors.toList());
  }

  @PostMapping(value = "/wishlist")
  public ResponseEntity<Void> create(@RequestBody WishItemDto resource) {
    WishItem wishItem = convertToWishItem(resource);
    
    String id = service.create(wishItem).getId();
    
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
        "/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(location).build();  
  }

  @GetMapping(value = "/wishlist/{id}")
  public WishItemDto findOne(@PathVariable String id) {
    
    WishItem wishItem = service.findOne(id);
    
    return convertToWishItemDto(wishItem);
  }

  private WishItem convertToWishItem(WishItemDto resource) {
    return this.modelMapper.map(resource, WishItem.Builder.class).build();
  }

  private WishItemDto convertToWishItemDto(WishItem wish) {
    return modelMapper.map(wish, WishItemDto.class);
  }

}
