package cbyyd_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    Button login;
    Button register;

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        login=new Button();
        login.setText("Login");

        register=new Button();
        register.setText("Register");

        StackPane layout=new StackPane();
        layout.getChildren().add(login);
        layout.getChildren().add(register);

        Scene scene =new Scene(layout,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
