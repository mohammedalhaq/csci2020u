package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class Main extends Application {
    private String username,password,fullName,email,phone;
    private TextField usernameText,nameText, emailText, phoneText;
    private PasswordField passwordText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab04 Solution");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(10, 10, 10, 10));

        Label labelUser = new Label("Username: ");
        usernameText = new TextField();
        usernameText.setPromptText("Username");

        Label labelPassword = new Label("Password: ");
        passwordText = new PasswordField();
        passwordText.setPromptText("Password");

        Label labelName = new Label("Full Name: ");
        nameText = new TextField();
        nameText.setPromptText("Full Name");

        Label labelEmail = new Label("Email: ");
        emailText = new TextField();
        emailText.setPromptText("Email");

        Label labelPhone = new Label("Phone #: ");
        phoneText = new TextField();
        phoneText.setPromptText("Phone #");

        Label labelDate = new Label("Date: ");
        labelDate = new Label("Date: ");

        DatePicker datepicker = new DatePicker();
        gridpane.add(labelUser, 0, 0);
        gridpane.add(labelPassword, 0, 1);
        gridpane.add(labelName, 0, 2);
        gridpane.add(labelEmail, 0, 3);
        gridpane.add(labelPhone, 0, 4);
        gridpane.add(labelDate, 0, 5);
        gridpane.add(usernameText, 1, 0);
        gridpane.add(passwordText, 1, 1);
        gridpane.add(nameText, 1, 2);
        gridpane.add(emailText, 1, 3);
        gridpane.add(phoneText, 1, 4);
        gridpane.add(datepicker, 1, 5);


        Button button = new Button("Register");
        gridpane.add(button, 1, 6);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                username = usernameText.getText();
                password = passwordText.getText();
                fullName = nameText.getText();
                email = emailText.getText();
                phone = phoneText.getText();
                System.out.println(username);
                System.out.println(password);
                System.out.println(fullName);
                System.out.println(email);
                System.out.println(phone);
                System.out.println(datepicker.getValue());
            }
        });

        Scene scene = new Scene(gridpane, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


        public static void main(String[] args) {
        launch(args);
    }
}
