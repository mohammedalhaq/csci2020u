package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;

public class Main extends Application {
    static String currentFile = "default.csv"; //default file to test (change it on proper usage)
    static TableView<StudentRecord> tableView; //initializes a tableview to display
    static ObservableList<StudentRecord> marks = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 08");

        tableView = new TableView<>();
        setColumns();
        populate();


        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuNew = new MenuItem("New");
        menuNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableView.getItems().clear();
            }
        });

        MenuItem menuOpen = new MenuItem("Open");
        menuOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("."));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Chart", "*.csv"));
                File file = fileChooser.showOpenDialog(primaryStage);
                currentFile = file.getName();
                load(file);

            }
        });

        MenuItem menuSave = new MenuItem ("Save");
        menuSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                     if (currentFile!=null) {
                         save();
                     } else {
                         saveAs(primaryStage);
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        MenuItem menuSaveAs = new MenuItem("Save as");
        menuSaveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveAs(primaryStage);
            }
        });

        MenuItem menuExit = new MenuItem ("Exit");
        menuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        menuFile.getItems().addAll(menuNew, menuOpen, menuSave, menuSaveAs, menuExit);
        menuBar.getMenus().add(menuFile);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));


        Label idLabel = new Label("SID");
        TextField idField = new TextField();
        idField.setPromptText("SID");

        Label midtermLabel = new Label("Midterm");
        TextField midtermField = new TextField();
        midtermField.setPromptText("Midterm/100");

        Label assignmentLabel = new Label("Assignment");
        TextField assignmentField = new TextField();
        assignmentField.setPromptText("Assignment/100");

        Label finalLabel = new Label("Final Exam");
        TextField finalField = new TextField();
        finalField.setPromptText("Final/100");

        Button button = new Button("Add");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                marks.add(new StudentRecord(idField.getText(), Float.valueOf(assignmentField.getText()), Float.valueOf(midtermField.getText()), Float.valueOf(finalField.getText())));
            }
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(midtermLabel, 0, 1);
        gridPane.add(midtermField, 1, 1);
        gridPane.add(assignmentLabel, 2, 0);
        gridPane.add(assignmentField, 3, 0);
        gridPane.add(finalLabel, 2, 1);
        gridPane.add(finalField, 3, 1);
        gridPane.add(button, 0, 2);


        gridPane.setMargin(idField,new Insets(15));
        gridPane.setMargin(midtermField,new Insets(15));
        gridPane.setMargin(assignmentField,new Insets(15));
        gridPane.setMargin(finalField,new Insets(15));
        gridPane.setMargin(button,new Insets(15));

        //creates a stackpane to display tableView
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tableView);
        VBox vBox = new VBox(menuBar,stackPane, gridPane);

        Scene scene = new Scene(vBox);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    System.out.println("Do something");
                }
            }
        });

        final KeyCombination toSave = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
            scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void saveAs(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", "*.csv"));
        File file = fileChooser.showSaveDialog(primaryStage);
        currentFile = file.getName();
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() throws IOException{
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(currentFile));
            String titles = "SID" + "," + "Assignments" + "," + "Midterm" + "," + "Final Exam";
            writer.println(titles);
            for (StudentRecord record : marks){
                String line = record.getId() + "," + record.getAssignment() + "," + record.getMidterm() + "," + record.getFinalExam();
                writer.println(line);
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setColumns(){
        //creates the Student Id Column and fills it
        TableColumn<StudentRecord, String> idCol = new TableColumn<>("SID");
        idCol.setMinWidth(138); //sets width of the column
        idCol.setCellValueFactory(new PropertyValueFactory<>("id")); //sets the data in the cell to be ID

        //creates the assignment Column and fills it
        TableColumn<StudentRecord, String> assignmentCol = new TableColumn<>("Assignments");
        assignmentCol.setMinWidth(92);
        assignmentCol.setCellValueFactory(new PropertyValueFactory<>("assignment"));

        //creates the midterm Column and fills it
        TableColumn<StudentRecord, String> midtermCol = new TableColumn<>("Midterm");
        midtermCol.setMinWidth(92);
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        //creates the final exam Column and fills it
        TableColumn<StudentRecord, String> finalCol = new TableColumn<>("Final Exam");
        finalCol.setMinWidth(92);
        finalCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        //creates the final mark Column and fills it
        TableColumn<StudentRecord, String> markCol = new TableColumn<>("Final Mark");
        markCol.setMinWidth(92);
        markCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        //creates the letter grade Column and fills it
        TableColumn<StudentRecord, String> letterCol = new TableColumn<>("Letter Grade");
        letterCol.setMinWidth(92);
        letterCol.setCellValueFactory(new PropertyValueFactory<>("letterG"));

        tableView.setItems(marks);
        //sets the items in the tableView to the test data
        tableView.getColumns().addAll(idCol, assignmentCol, midtermCol, finalCol, markCol, letterCol); //adds all the columns to TableView

    }

    public static void populate(){
        /* default student records for testing purposes */
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));

    }

    public static void load(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            tableView = new TableView<>();
            marks.clear();
            setColumns();
            int count = 0;
            String line;
            while ((line=bufferedReader.readLine())!=null){
                if (count !=0){
                    String[] data = line.split(",");
                    StudentRecord temp = new StudentRecord(data[0], Float.valueOf(data[1]), Float.valueOf(data[2]), Float.valueOf(data[3]));
                    marks.add(temp);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableView.setItems(marks);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
