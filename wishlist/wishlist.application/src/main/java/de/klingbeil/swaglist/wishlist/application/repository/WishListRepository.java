package de.klingbeil.swaglist.wishlist.application.repository;

import java.util.Arrays;
import java.util.List;
import de.klingbeil.swaglist.wishlist.domain.WishItem;
public class WishListRepository {

  public List<WishItem> findAll() {
    WishItem wishItem1 =
        new WishItem.Builder("iPhoneX").description("https://www.apple.com/de/iphone/").build();
    WishItem wishItem2 = new WishItem.Builder("Die Legenden von Andor")
        .description(
            "KOSMOS 691745 - Die Legenden von Andor, Kennerspiel des Jahres 2013 Grundspiel + Erweiterung 'der Sternenschild'")
        .build();
    WishItem wishItem3 = new WishItem.Builder("REST und HTTP")
        .description("REST und HTTP: Entwicklung und Integration nach dem Architekturstil des Web")
        .build();
    WishItem wishItem4 = new WishItem.Builder("Plattform Taschenbuch ")
        .description(
            "Männer wollen vor allem vögeln. Die Verführung interessiert nur ein paar Typen, die kein wirklich aufregendes Berufsleben und auch sonst keine Interessen im Leben haben.' Noch immer gibt Michel Houellebecq den Zyniker vom Dienst. Auch in der Hörspiel-Adaption seines Skandal-Romans Plattform kommt Michel,")
        .build();
    WishItem wishItem5 = new WishItem.Builder("Philips Hue White & Color Ambiance")
        .description(
            "Philips Hue White & Color Ambiance E27 LED Lampe Starter Set, drei Lampen 3. Generation inkl. Bridge, dimmbar, bis zu 16 Millionen Farben, steuerbar via App, kompatibel mit Amazon Alexa (Echo, Echo Dot)")
        .build();
    WishItem wishItem6 = new WishItem.Builder("Cupcake Ipsum")
        .description(
            "Philips How about using auto-generated text that will actually make people love your project even more? With Cupcake Ipsum, you can create a sweet, sugar-coated paragraph of text:")
        .build();
    WishItem wishItem7 = new WishItem.Builder("Picksum Ipsum")
        .description(
            "Picksum Ipsum is a badass text generator that will give you the best lines from some of the biggest Hollywood legends, including Clint Eastwood, Michael Caine, Jim Carrey and Morgan Freeman:")
        .build();
    WishItem wishItem8 = new WishItem.Builder("Lorizzle")
        .description(
            "Lorizzle generates some 'gangsta' dummy text, so your content can look funky fresh while waiting on the approved text")
        .build();

    return Arrays.asList(wishItem1, wishItem2, wishItem3, wishItem4, wishItem5, wishItem6,
        wishItem7, wishItem8);
  }
}
