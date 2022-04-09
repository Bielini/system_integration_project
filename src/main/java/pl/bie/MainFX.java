package pl.bie;

import jakarta.xml.ws.Endpoint;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.bie.api.rest.RESTInterface;
import pl.bie.api.rest.RESTInterfaceImpl;
import pl.bie.api.soap.SOAPInterfaceImpl;
import pl.bie.model.Voivodeship;
import pl.bie.model.VoivodeshipCategories;
import pl.bie.model.VoivodeshipNames;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceHibernateImpl;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MainFX extends Application {
    private VoivodeshipService voivodeshipServiceXML = new VoivodeshipServiceXMLImpl();
    private VoivodeshipService voivodeshipServiceHIB = new VoivodeshipServiceHibernateImpl();
    private RESTInterface restInterface = new RESTInterfaceImpl();

    private Endpoint soapEndpoint;

    private final VBox root = new VBox();

    private final HBox functionsBox = new HBox();
    private final VBox chartsControlBox = new VBox();
    private final HBox tablesBox = new HBox();

    private final HBox xmlButtonsBox = new HBox();
    private final HBox databaseButtonsBox = new HBox();

    private final VBox webServicesBox = new VBox();

    private final VBox dataContainersBox = new VBox();


    private final VBox chartsBox = new VBox();
    private final VBox footerBox = new VBox();

    private final ComboBox<VoivodeshipNames> voivNames = new ComboBox<VoivodeshipNames>();
    private final ComboBox<VoivodeshipCategories> voivCategories = new ComboBox<VoivodeshipCategories>();

    private final Button resultButton = new Button("Generuj wykres");
    private final Button xmlButton = new Button("Odczyt XML");
    private final Button databaseButton = new Button("Odczyt Bazydanych");
    private final Button xmlWButton = new Button("Zapis XML");
    private final Button databaseWButton = new Button("Zapis Bazydanych");

    private final ToggleButton restSwitch = new ToggleButton("REST API start.");
    private final ToggleButton soapSwitch = new ToggleButton("SOAP API start.");
    private final Label chartsControlLabel = new Label("Parametry wykresów");
    private final Label webServicesLabel = new Label(" Serwisy webowe ");
    private final Label dataContainersLabel = new Label("   Przechowywanie  \n        danych");

    private final Label footer = new Label("Projekt wykonali: Weronika Frączek oraz Piotr Bielawski\n" +
            "Źródło danych: https://api.stat.gov.pl/Home/Index");
    private LineChart<String, Number> lineChart;
    private List<Voivodeship> read;


    @Override
    public void start(Stage stage) throws Exception {
        addComponents();
        setActions();

        Scene view = new Scene(root, 1000, 685);
        stage.setScene(view);
        stage.setTitle("Porównanie danych statystycznch przed i w trakcie pandemii.");
        stage.setResizable(false);
        stage.show();
    }

    private void addComponents() {

        voivNames.getItems().addAll(VoivodeshipNames.values());
        read = voivodeshipServiceXML.read();
        voivCategories.getItems().addAll(
                VoivodeshipCategories.PRZYCHODY_RESTAURACJI,
                VoivodeshipCategories.LICZBA_WYPADKÓW_SAMOCHODOWYCH,
                VoivodeshipCategories.POJEMNOŚCI_IMPREZ_MASOWYCH,
                VoivodeshipCategories.LICZBA_ŚMIERCI,
                VoivodeshipCategories.ŚREDNIE_ZAROBKI_BRUTTO
        );


        prepareTables();
        lineChart = initializeChart();
        restSwitch.setStyle("-fx-background-color: #67bb3b;");
        soapSwitch.setStyle("-fx-background-color: #67bb3b;");

        footer.setAlignment(Pos.BOTTOM_LEFT);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 22);
        footer.setFont(font);


///*******************************************************
        voivNames.setPadding(new Insets(5));
        voivCategories.setPadding(new Insets(5));
        resultButton.setPadding(new Insets(5));

        font = Font.font("Verdana", FontWeight.BOLD, 22);
        chartsControlLabel.setFont(font);
        tablesBox.getChildren().addAll(voivNames, voivCategories);
        tablesBox.setPadding(new Insets(5));
        chartsControlBox.setPadding(new Insets(5));
        tablesBox.setSpacing(5);
        chartsControlBox.setBorder(new Border(
                new BorderStroke(Paint.valueOf("#686b69"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        chartsControlBox.setAlignment(Pos.CENTER);
        chartsControlBox.getChildren().addAll(chartsControlLabel, tablesBox, resultButton);

//---------------------------------------------------------------------------

        webServicesLabel.setFont(font);
        webServicesBox.getChildren().addAll(webServicesLabel, restSwitch, soapSwitch);
        webServicesBox.setAlignment(Pos.CENTER);
        webServicesBox.setSpacing(10);

        webServicesBox.setBorder(new Border(
                new BorderStroke(Paint.valueOf("#686b69"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


//---------------------------------------------------------------------------
        databaseButtonsBox.getChildren().addAll(databaseButton,databaseWButton);
        databaseButtonsBox.setSpacing(5);
        databaseButtonsBox.setPadding(new Insets(5));
        databaseButtonsBox.setAlignment(Pos.CENTER);

        xmlButtonsBox.getChildren().addAll(xmlButton,xmlWButton);
        xmlButtonsBox.setSpacing(5);
        xmlButtonsBox.setAlignment(Pos.CENTER);
        xmlButtonsBox.setPadding(new Insets(5));

        dataContainersLabel.setFont(font);
        dataContainersBox.setAlignment(Pos.CENTER);
        dataContainersBox.setSpacing(5);
        dataContainersBox.getChildren().addAll(dataContainersLabel, xmlButtonsBox, databaseButtonsBox);
        dataContainersBox.setBorder(new Border(
                new BorderStroke(Paint.valueOf("#686b69"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        functionsBox.getChildren().addAll(chartsControlBox, webServicesBox, dataContainersBox);
        functionsBox.setPadding(new Insets(20));
        functionsBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
///*******************************************************

        chartsBox.getChildren().add(lineChart);
        footerBox.getChildren().add(footer);
        footerBox.setPadding(new Insets(30));
        footerBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        root.getChildren().addAll(
                functionsBox, chartsBox, footerBox
        );
    }


    private LineChart<String, Number> getStringNumberLineChart(String name, VoivodeshipCategories category) {


        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Year");
        yAxis.setLabel("Value");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Kategoria: "+category + ", Województwo: " + name);
        lineChart.setLegendVisible(false);

        XYChart.Series voivodeships = new XYChart.Series();

        for (Voivodeship voivodeship : read) {

            if (voivodeship.getSourceFile().equals(category.getValue())) {
                if (voivodeship.getName().equals(name)) {

                    TreeMap<String, Double> sorted = new TreeMap<>(voivodeship.getValueByYears());

                    for (Map.Entry<String, Double> stringDoubleEntry : sorted.entrySet()) {
                        voivodeships.getData().add(new XYChart.Data(stringDoubleEntry.getKey(), stringDoubleEntry.getValue()));
                    }
                }
            }
        }

        lineChart.getData().add(voivodeships);
        return lineChart;
    }

    private void setActions() {
        resultButton.setOnAction(actionEvent -> generateChart());
        restSwitch.setOnAction(actionEvent -> restApiControl());
        soapSwitch.setOnAction(actionEvent -> soapApiControl());
        xmlButton.setOnAction(actionEvent -> xmlRead());
        databaseButton.setOnAction(actionEvent -> databaseRead());
        xmlWButton.setOnAction(actionEvent -> xmlSave());
        databaseWButton.setOnAction(actionEvent -> databaseSave());
    }

    private void databaseRead() {
        voivodeshipServiceHIB.read();
        voivodeshipServiceHIB.printAll();
    }


    private void xmlRead() {
        voivodeshipServiceXML.printAll();
    }

    private void databaseSave() {
        voivodeshipServiceHIB.save();
    }


    private void xmlSave() {
        voivodeshipServiceXML.save();
    }

    private void restApiControl() {
        if (restSwitch.isSelected()) {
            try {
                restInterface.start();
                restSwitch.setText("REST API stop.");
                restSwitch.setStyle("-fx-background-color: #ff0000;");
            } catch (ConnectException e) {
                System.err.println("Probably server is off!");
            }

        } else {
            restInterface.stop();
            restSwitch.setText("REST API start.");
            restSwitch.setStyle("-fx-background-color: #67bb3b;");
        }
    }

    private void soapApiControl() {
        if (soapSwitch.isSelected()) {
            soapEndpoint = Endpoint.publish("http://localhost:7779/statistics", new SOAPInterfaceImpl());
            soapSwitch.setText("SOAP API stop.");
            soapSwitch.setStyle("-fx-background-color: #ff0000;");
        } else {
            soapEndpoint.stop();
            soapSwitch.setText("SOAP API start.");
            soapSwitch.setStyle("-fx-background-color: #67bb3b;");
        }
    }

    private void generateChart() {
        String name = voivNames.getSelectionModel().getSelectedItem().getValue();
        VoivodeshipCategories category = voivCategories.getSelectionModel().getSelectedItem();
        System.out.println(name + "  " + category);
        lineChart = getStringNumberLineChart(name, category);
        chartsBox.getChildren().clear();
        chartsBox.getChildren().add(lineChart);

    }

    private void prepareTables() {
        voivNames.getSelectionModel().select(0);
        voivCategories.getSelectionModel().select(0);

    }

    private LineChart<String, Number> initializeChart() {
        return getStringNumberLineChart(
                voivNames.getSelectionModel().getSelectedItem().getValue(),
                voivCategories.getSelectionModel().getSelectedItem());
    }

    public static void main(String[] args) {
        launch();
    }
}
