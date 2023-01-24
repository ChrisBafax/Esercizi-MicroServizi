package com.myrestaurant.store.PizzaService;

import com.myrestaurant.store.PizzaService.dao.PizzaRepository;
import com.myrestaurant.store.PizzaService.model.Pizza;
import com.myrestaurant.store.PizzaService.model.Topping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class PizzaServiceApplicationTests {

    @Autowired
    PizzaRepository pizzaRepository;

    @Test
    void populateDBTP() {
        // To use remove the comment around the code below.
        // List of Toppings

        Topping mozzarella = Topping.builder().name("Mozzarella locale").build();
        Topping pomodoroLocale = Topping.builder().name("Pomodoro locale").build();
        Topping basilico = Topping.builder().name("Basilico").build();
        Topping gorgonzola = Topping.builder().name("Gorgonzola locale").build();
        Topping provolone = Topping.builder().name("Provolone locale").build();
        Topping granaPadano = Topping.builder().name("Grana Padano").build();
        Topping fiorDiLatte = Topping.builder().name("Fior di Latte").build();
        Topping funghiPorciniDellaSila = Topping.builder().name("Funghi porcini della Sila").build();
        Topping provoloneDelMonaco = Topping.builder().name("Provolone del Monaco").build();
        Topping salsaDiPeperoniGialli = Topping.builder().name("Salsa di Peperoni gialli").build();
        Topping salsicciaDiFegatoSbriciolata = Topping.builder().name("Salsiccia di Fegato sbriciolata").build();
        Topping patateSilane = Topping.builder().name("Patate silane").build();
        Topping lardoDiMaialeNeroSilano = Topping.builder().name("Lardo di maiale nero silano").build();
        Topping riduzionePeperoniGialliEPrezzemolo = Topping.builder().name("Riduzione di Peperoni gialli e prezzemolo").build();
        Topping rucola = Topping.builder().name("Rucola").build();
        Topping ricottaDiPecoraAlloZafferano = Topping.builder().name("Ricotta di pecora allo zafferano").build();
        Topping pomodoriSecchi = Topping.builder().name("Pomodori secchi").build();
        Topping gherigliDiNoce = Topping.builder().name("Gherigli di noce").build();
        Topping pomodoriniRossi = Topping.builder().name("Pomodorini rossi").build();
        Topping speckMaialeNeroCalabrese = Topping.builder().name("Speck maiale nero calabrese").build();
        Topping granellaDiPistacchio = Topping.builder().name("Granella di pistacchio").build();
        Topping battutoDiPistacchioDiBronte = Topping.builder().name("Battuto di pistacchio di Bronte").build();
        Topping figliata = Topping.builder().name("Figliata").build();
        Topping mortadella = Topping.builder().name("Mortadella").build();
        Topping cremaDiGorgonzolaEPistacchio = Topping.builder().name("Crema di gorgonzola e pistacchio").build();
        Topping pacchetelleGialle = Topping.builder().name("Pacchetelle gialle").build();
        Topping bottargaDiMuggine = Topping.builder().name("Bottarga di muggine").build();
        Topping aliciDiCetara = Topping.builder().name("Alici di Cetara").build();
        Topping zesteDiLimoneDiCetraro = Topping.builder().name("Zeste di Limone di Cetraro").build();
        Topping stracciatella = Topping.builder().name("Stracciatella").build();


        // List of pizzas

        Pizza margherita = Pizza.builder().name("Margherita").favourite(true).toppings(Set.of(mozzarella, pomodoroLocale, basilico)).build();
        Pizza quattroFormaggi = Pizza.builder().name("4 Formaggi").toppings(Set.of(mozzarella, gorgonzola, provolone, granaPadano)).build();
        Pizza pizzaDAsila = Pizza.builder().name("Pizzad'Asila").toppings(Set.of(fiorDiLatte, patateSilane, funghiPorciniDellaSila, lardoDiMaialeNeroSilano)).build();
        Pizza pizzaSacroEProfano = Pizza.builder().name("Sacro e profano").toppings(Set.of(fiorDiLatte, salsaDiPeperoniGialli, salsicciaDiFegatoSbriciolata, funghiPorciniDellaSila, riduzionePeperoniGialliEPrezzemolo, provoloneDelMonaco)).build();
        Pizza pizzaDAccarezzare = Pizza.builder().name("Pizzad'Accarezzare").toppings(Set.of(fiorDiLatte, rucola, ricottaDiPecoraAlloZafferano, pomodoriSecchi, gherigliDiNoce)).build();
        Pizza pizzaDAlessandro = Pizza.builder().name("Pizzad'Alessandro").toppings(Set.of(fiorDiLatte, pomodoriniRossi, speckMaialeNeroCalabrese, granellaDiPistacchio, battutoDiPistacchioDiBronte)).build();
        Pizza pizzaEFigliata = Pizza.builder().name("Pizza e Figliata").toppings(Set.of(figliata, mortadella, cremaDiGorgonzolaEPistacchio)).build();
        Pizza pizzaBufalaDiMare = Pizza.builder().name("Pizza bufala di mare").toppings(Set.of(fiorDiLatte, pacchetelleGialle, bottargaDiMuggine, aliciDiCetara, zesteDiLimoneDiCetraro, stracciatella, basilico)).build();
        pizzaRepository.saveAll(List.of(margherita, quattroFormaggi, pizzaDAsila, pizzaSacroEProfano, pizzaDAccarezzare, pizzaDAlessandro, pizzaEFigliata, pizzaBufalaDiMare));

    }

}
