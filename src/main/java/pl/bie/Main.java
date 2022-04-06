package pl.bie;

import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();
        final List<String> paths = List.of(
                "restaurantsIncome.xml"
                ,"massiveParties.xml"
                ,"carAccidents.xml"
                ,"averageSalary.xml"
                ,"deathsNumber.xml");






        voivodeshipService.printData();
        List<Voivodeship> read = voivodeshipService.read(paths);

    }
}
