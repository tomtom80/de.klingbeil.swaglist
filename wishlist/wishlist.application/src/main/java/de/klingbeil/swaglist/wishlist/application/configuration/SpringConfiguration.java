package de.klingbeil.swaglist.wishlist.application.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import de.klingbeil.swaglist.wishlist.api.model.converter.WishItemDtoToWishItemConverter;

@Configuration
public class SpringConfiguration {
  
  @Bean
  public ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.addMappings(new WishItemDtoToWishItemConverter());
      return modelMapper;
  }
}
