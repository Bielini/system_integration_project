package pl.bie;

import com.sun.net.httpserver.HttpServer;
import jakarta.xml.ws.Endpoint;
import pl.bie.api.rest.RESTInterface;
import pl.bie.api.rest.RESTInterfaceImpl;
import pl.bie.api.soap.SOAPInterfaceImpl;
import pl.bie.entity.RecordEntity;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceHibernateImpl;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;


import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
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
            System.out.println("3 - SOAP start");
            System.out.println("4 - REST start");
            System.out.println("q - exit");
            String chosenOption = scanner.nextLine();

            switch (chosenOption) {
                case "1" -> subMenu(scanner, new VoivodeshipServiceXMLImpl());
                case "2" -> subMenu(scanner, new VoivodeshipServiceHibernateImpl());
                case "3" -> soapStart(scanner);
                case "4" -> restStart(scanner);
                case "q" -> System.exit(0);
                default -> System.out.println("Wrong option!");
            }
        }
    }

    private static void subMenu(Scanner scanner, VoivodeshipService voivodeshipService) {


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

    private static void soapStart(Scanner scanner) {
        Endpoint publish = Endpoint.publish("http://localhost:7779/statistics", new SOAPInterfaceImpl());
        System.out.println("SOAP api is running...");
        System.out.println("b - back    q - exit");

        while (true) {
            String chosenOption = scanner.nextLine();
            switch (chosenOption) {
                case "b" -> {
                    publish.stop();
                    System.out.println("SOAP api is stopped.");
                    mainMenu(scanner);
                }
                case "q" -> {
                    publish.stop();
                    System.out.println("SOAP api is stopped.");
                    System.out.println("App stopped...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong option!");
                }
            }
        }
    }

    private static void restStart(Scanner scanner) {
        RESTInterface restInterface = new RESTInterfaceImpl();
        try {
            restInterface.start();

            System.out.println("Rest api is running...");
            System.out.println("q - exit");

            while (true) {
                String chosenOption = scanner.nextLine();
                switch (chosenOption) {
                    case "b" -> {
                        restInterface.stop();
                        System.out.println("REST api is stopped.");
                        mainMenu(scanner);
                    }
                    case "q" -> {
                        restInterface.stop();
                        System.out.println("REST api is stopped.");
                        System.out.println("App stopped...");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Wrong option!");
                    }
                }
            }
        } catch (ConnectException e) {
            System.err.println("Probably server is off!");
        }
    }
}
