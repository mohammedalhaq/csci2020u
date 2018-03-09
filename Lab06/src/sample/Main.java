package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FlowPane root = new FlowPane();
        primaryStage.setTitle("Lab 06");

        final CategoryAxis x = new CategoryAxis();
        final NumberAxis y = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<String, Number>(x, y);

        XYChart.Series housing= new XYChart.Series();
        housing.setName("Average Housing");
        XYChart.Series commerical = new XYChart.Series();
        commerical.setName("Average Commercial");
        barChart.setLegendVisible(false);

        String[] year = {"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"};
        for (int i =0; i<=7;i++){
            XYChart.Data data = new XYChart.Data(year[i],avgHousingPricesByYear[i]);
            //data.getNode().setStyle("-fx-bar-fill: blue;");
            housing.getData().add(data);
            data = new XYChart.Data(year[i],avgCommercialPricesByYear[i]);
            //data.getNode().setStyle("-fx-fill: red");
            commerical.getData().add(data);
        }
        barChart.getData().addAll(housing,commerical);

        ObservableList<PieChart.Data> data= FXCollections.observableArrayList();
        for (int i =0; i < 6; i++) {
            PieChart.Data temp = new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]);
            temp.getNode().setStyle("-fx-pie-color: " + pieColours[i] + ";");
            data.add(temp);
        }
        final PieChart pieChart = new PieChart(data);
        pieChart.setLegendVisible(false);
        pieChart.setTitle("Purchases by Age Group");

        root.getChildren().addAll(barChart, pieChart);
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    private static double[] avgHousingPricesByYear = {
            247381.0, 264171.4,287715.3, 294736.1,
            308431.4,322635.9, 340253.0, 363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    public static void main(String[] args) {
        launch(args);
    }
}
