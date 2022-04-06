package pl.bie;

import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();

        List<Voivodeship> read = voivodeshipService.read("restaurantsIncome.xml");
        voivodeshipService.printData();

    }
}
