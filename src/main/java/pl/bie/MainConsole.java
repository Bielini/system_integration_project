package pl.bie;

import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceHibernateImpl;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;

import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);

    }

    private static void mainMenu(Scanner scanner) {


        while (true) {
            System.out.println("Menu");
            System.out.println("1 - Xml Functions");
            System.out.println("2 - Database Functions");
            System.out.println("q - exit");
            String chosenOption = scanner.nextLine();

            switch (chosenOption) {
                case "1" -> subMenu(scanner, new VoivodeshipServiceXMLImpl());
                case "2" -> subMenu(scanner, new VoivodeshipServiceHibernateImpl());
                case "q" -> System.exit(0);
                default -> System.out.println("Wrong option!");
            }
        }
    }

    private static void subMenu(Scanner scanner,VoivodeshipService voivodeshipService) {


        while (true) {
            System.out.println("Functions");
            System.out.println("1 - Read");
            System.out.println("2 - Print");
            System.out.println("3 - Save");

            System.out.println("b - back    q - exit");
            String chosenOption = scanner.nextLine();

            switch (chosenOption) {
                case "1" -> voivodeshipService.read();
                case "2" -> voivodeshipService.printAll();
                case "3" -> voivodeshipService.save();
                case "b" -> mainMenu(scanner);
                case "q" -> System.exit(0);
                default -> System.out.println("Wrong option!");
            }
        }
    }


}
