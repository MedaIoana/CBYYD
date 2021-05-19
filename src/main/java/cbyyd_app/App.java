package cbyyd_app;

import cbyyd_app.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import  org.apache.commons.lang3.StringUtils;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 800, 455));
        primaryStage.setTitle("CBYYD");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
