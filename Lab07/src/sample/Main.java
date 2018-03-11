package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 07");

        BufferedReader bufferedReader = new BufferedReader(new FileReader("weatherwarnings-2015.csv"));
        HashMap<String, Integer> map = new HashMap<>();
        map.put("FLASH FLOOD", 0);
        map.put("SEVERE THUNDERSTORM", 0);
        map.put("SPECIAL MARINE", 0);
        map.put("TORNADO", 0);

        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] country = line.split(",");
            if (map.containsKey(country[5])){
                int value = map.get(country[5]) +1;
                map.put(country[5], value);
            }
        }

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();


        for (Map.Entry<String, Integer> entry : map.entrySet()){
            PieChart.Data temp = new PieChart.Data(entry.getKey(),entry.getValue());
            data.add(temp);
        }

        final PieChart pie= new PieChart(data);
        pie.setLabelsVisible(false);
        pie.setLegendSide(Side.LEFT);

        primaryStage.setScene(new Scene(pie));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
