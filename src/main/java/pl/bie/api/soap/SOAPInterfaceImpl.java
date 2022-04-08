package pl.bie.api.soap;

import jakarta.jws.WebService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;


import java.util.List;
import java.util.Locale;
import java.util.Map;

@WebService(endpointInterface = "pl.bie.api.soap.SOAPInterface")
public class SOAPInterfaceImpl implements SOAPInterface {

    private List<Voivodeship> voivodeshipList;

    @Override
    public String getSpecificYearData(String year) {
        final VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();
        voivodeshipList = voivodeshipService.read();
        if (voivodeshipList.isEmpty()) {
            System.out.println("empty list");
            return "Empty list. Consider call read xml first!";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Voivodeship voivodeship : voivodeshipList) {

                stringBuilder.append("Name: ");
                stringBuilder.append(voivodeship.getName());
                stringBuilder.append("\n");
                stringBuilder.append("Category: ");
                stringBuilder.append(voivodeship.getSourceFile());
                stringBuilder.append("\n");

                for (Map.Entry<String, Double> stringDoubleEntry : voivodeship.getValueByYears().entrySet()) {

                    if (year.equals(stringDoubleEntry.getKey())) {
                        stringBuilder.append("Year: ");
                        stringBuilder.append(stringDoubleEntry.getKey());
                        stringBuilder.append("      Value: ");
                        stringBuilder.append(stringDoubleEntry.getValue());
                        stringBuilder.append("\n");
                    }

                }
            }
            System.out.println("getAllByName method called.");
            return stringBuilder.toString();
        }
    }

    @Override
    public String getAllByName(String name) {
        final VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();
        voivodeshipList = voivodeshipService.read();
        if (voivodeshipList.isEmpty()) {
            System.out.println("empty list");
            return "Empty list. Consider call read xml first!";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Voivodeship voivodeship : voivodeshipList) {
                if (name.toUpperCase(Locale.ROOT).equals(voivodeship.getName())) {
                    stringBuilder.append("Name: ");
                    stringBuilder.append(voivodeship.getName());
                    stringBuilder.append("\n");
                    stringBuilder.append("Category: ");
                    stringBuilder.append(voivodeship.getSourceFile());
                    stringBuilder.append("\n");

                    for (Map.Entry<String, Double> stringDoubleEntry : voivodeship.getValueByYears().entrySet()) {
                        stringBuilder.append("Year: ");
                        stringBuilder.append(stringDoubleEntry.getKey());
                        stringBuilder.append("      Value: ");
                        stringBuilder.append(stringDoubleEntry.getValue());
                        stringBuilder.append("\n");

                    }
                }

            }

            System.out.println("getAllByName method called.");

            return stringBuilder.toString();
        }


    }

    @Override
    public String getSpecificCategoryData(String category) {
        final VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();
        voivodeshipList = voivodeshipService.read();
        if (voivodeshipList.isEmpty()) {
            System.out.println("empty list");
            return "Empty list. Consider call read xml first!";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            category = category + ".xml";
            for (Voivodeship voivodeship : voivodeshipList) {
                if (category.equals(voivodeship.getSourceFile())) {
                    stringBuilder.append("Name: ");
                    stringBuilder.append(voivodeship.getName());
                    stringBuilder.append("\n");
                    stringBuilder.append("Category: ");
                    stringBuilder.append(voivodeship.getSourceFile());
                    stringBuilder.append("\n");

                    for (Map.Entry<String, Double> stringDoubleEntry : voivodeship.getValueByYears().entrySet()) {
                        stringBuilder.append("Year: ");
                        stringBuilder.append(stringDoubleEntry.getKey());
                        stringBuilder.append("      Value: ");
                        stringBuilder.append(stringDoubleEntry.getValue());
                        stringBuilder.append("\n");

                    }
                }

            }

            System.out.println("getSpecificCategoryData method called.");

            return stringBuilder.toString();
        }
    }
}
