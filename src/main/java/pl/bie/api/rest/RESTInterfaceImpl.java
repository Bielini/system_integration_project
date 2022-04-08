package pl.bie.api.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.mysql.cj.xdevapi.JsonString;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import pl.bie.entity.RecordEntity;
import pl.bie.executor.HibernateExecutor;
import pl.bie.executor.ORMExecutor;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RESTInterfaceImpl implements RESTInterface {
    private final ORMExecutor ormExecutor = new HibernateExecutor();
    private HttpServer server = null;
    private HttpExchange httpExchange;

    @Override
    public void start() {
        ormExecutor.bootstrap();
        List<RecordEntity> read = ormExecutor.read();
        int serverPort = 8000;

        try {
            server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.createContext("/api/statistics", (exchange -> {
            System.out.println("Print all url called");
            String respText = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(read);

            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));


        server.createContext("/api/statistics/id", (exchange -> {
            System.out.println("Print by id url called");
            Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
            Integer id = Integer.parseInt(parms.get("id"));

            Optional<RecordEntity> first = read.stream().filter(x -> id.equals(x.getId())).findFirst();

            String respText = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(first.get());

            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));
        server.createContext("/api/info", (exchange -> {

            System.out.println("Info url called");
            String respText ="Available urls: \n" +
                    "/api/statistics - print all data\n" +
                    "/api/statistics/id?id={index} - print data with index\n" +
                    "/api/info - print method info";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));


        server.setExecutor(null); // creates a default executor
        server.start();
    }

    @Override
    public void stop() {
        this.server.stop(1);
    }


    public Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {

            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }

        return result;
    }
}
