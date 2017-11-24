package de.klingbeil.swaglist.wishlist.application.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

@Service
public class WishListServiceImpl implements WishListService {

  private List<WishItem> wishes;

  public WishListServiceImpl() {
    wishes = new ArrayList<>();
    wishes.addAll(generateInitialWishes());
  }

  @Override
  public List<WishItem> findAll() {
    return wishes;
  }
  
  @Override
  public WishItem findOne(String id){
    return wishes.stream().filter(wish -> wish.getId().equals(id))
        .reduce((a, b) -> {
          throw new IllegalStateException("Multiple elements: " + a + ", " + b);
      })
      .get();
  }

  @Override
  public WishItem create(WishItem wishItem) {
    UUID id = UUID.randomUUID();
    WishItem item = new WishItem.Builder(id.toString(), wishItem.getName())
        .description(wishItem.getDescription()).build();
    wishes.add(item);
    return item;
  }

  private List<WishItem> generateInitialWishes() {
    WishItem wishItem1 = new WishItem.Builder(UUID.randomUUID().toString(), "iPhoneX")
        .description("https://www.apple.com/de/iphone/").build();
    WishItem wishItem2 =
        new WishItem.Builder(UUID.randomUUID().toString(), "Die Legenden von Andor")
            .description(
                "KOSMOS 691745 - Die Legenden von Andor, Kennerspiel des Jahres 2013 Grundspiel + Erweiterung 'der Sternenschild'")
            .build();
    WishItem wishItem3 = new WishItem.Builder(UUID.randomUUID().toString(), "REST und HTTP")
        .description("REST und HTTP: Entwicklung und Integration nach dem Architekturstil des Web")
        .build();
    WishItem wishItem4 =
        new WishItem.Builder(UUID.randomUUID().toString(), "Plattform Taschenbuch ")
            .description(
                "Männer wollen vor allem vögeln. Die Verführung interessiert nur ein paar Typen, die kein wirklich aufregendes Berufsleben und auch sonst keine Interessen im Leben haben.' Noch immer gibt Michel Houellebecq den Zyniker vom Dienst. Auch in der Hörspiel-Adaption seines Skandal-Romans Plattform kommt Michel,")
            .build();
    WishItem wishItem5 =
        new WishItem.Builder(UUID.randomUUID().toString(), "Philips Hue White & Color Ambiance")
            .description(
                "Philips Hue White & Color Ambiance E27 LED Lampe Starter Set, drei Lampen 3. Generation inkl. Bridge, dimmbar, bis zu 16 Millionen Farben, steuerbar via App, kompatibel mit Amazon Alexa (Echo, Echo Dot)")
            .build();
    WishItem wishItem6 = new WishItem.Builder(UUID.randomUUID().toString(), "Cupcake Ipsum")
        .description(
            "Philips How about using auto-generated text that will actually make people love your project even more? With Cupcake Ipsum, you can create a sweet, sugar-coated paragraph of text:")
        .build();
    WishItem wishItem7 = new WishItem.Builder(UUID.randomUUID().toString(), "Picksum Ipsum")
        .description(
            "Picksum Ipsum is a badass text generator that will give you the best lines from some of the biggest Hollywood legends, including Clint Eastwood, Michael Caine, Jim Carrey and Morgan Freeman:")
        .build();
    WishItem wishItem8 = new WishItem.Builder(UUID.randomUUID().toString(), "Lorizzle")
        .description(
            "Lorizzle generates some 'gangsta' dummy text, so your content can look funky fresh while waiting on the approved text")
        .build();

    return Arrays.asList(wishItem1, wishItem2, wishItem3, wishItem4, wishItem5, wishItem6,
        wishItem7, wishItem8);
  }
}
