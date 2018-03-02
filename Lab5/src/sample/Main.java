package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 05 Solutions");
        TableView<StudentRecord> tableView = new TableView<>(); //initializes a tableview to display

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

        //creates a DataSource object and populates tableView
        DataSource dataSource = new DataSource();
        tableView.setItems(dataSource.getAllMarks()); //sets the items in the tableView to the test data
        tableView.getColumns().addAll(idCol, assignmentCol, midtermCol, finalCol, markCol, letterCol); //adds all the columns to TableView

        //creates a stackpane to display tableView
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tableView);

        primaryStage.setScene(new Scene(stackPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
