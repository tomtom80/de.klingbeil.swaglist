package de.klingbeil.swaglist.wishlist.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
  
  @Bean
  public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("de.klingbeil.swaglist.wishlist"))
              .paths(Predicates.not(PathSelectors.regex("/error")))
              .build()
              .apiInfo(apiInfo());
  }
  
  private ApiInfo apiInfo() {
      Contact contact = new Contact("Tom Klingbeil", "", "tom.klingbeil@gmail.com");
      return new ApiInfoBuilder()
              .title("SWAGlist")
              .description("Let your wishes come true.")
              .termsOfServiceUrl("http://springfox.io")
              .contact(contact)
              .license("Apache License Version 2.0")
              .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
              .version("1.0")
              .build();
  }
}
