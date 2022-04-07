package pl.bie;

import pl.bie.executor.HibernateExecutor;
import pl.bie.executor.ORMExecutor;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;

import java.util.List;

public class MainConsole {
    public static void main(String[] args) {
        VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();
        ORMExecutor jpaEx = new HibernateExecutor();

        final List<String> paths = List.of(
                "restaurantsIncome.xml"
                , "massiveParties.xml"
                , "carAccidents.xml"
                , "averageSalary.xml"
                , "deathsNumber.xml");


//        boolean programRunning = true;
//        final Scanner scanner = new Scanner(System.in);
//        while (programRunning) {
//            System.out.println("Menu");
//            System.out.println("1 - Xml functions");
//            System.out.println("2 - JPA");
//            System.out.println("q - exit");
//            String chosenOption = scanner.nextLine();
//
//            switch (chosenOption) {
//                case "1":
//
//                    break;
//                case "2":
//                    hibernate.bootstrap();
//                    hibernate.execute();
//                    break;
//                case "q":
//                    hibernateExecutor.shutDown();
//                    jpaExecutor.shutDown();
//                    programRunning = false;
//                    break;
//                default:
//                    System.out.println("Wrong option!");
//            }
//        }

            jpaEx.bootstrap();

//        voivodeshipService.read(paths);
//        voivodeshipService.printData();
//        voivodeshipService.save("combineData.xml");

    }
}