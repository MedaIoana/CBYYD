package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class seeTreatmentsControllers implements Initializable {

    @FXML
    public ListView list;

    @FXML
    Text backMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        list.getItems().addAll(UserService.seePatients(LoginRegistrationControllers.getUsernameD()));
    }

    @FXML
    public void BackHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/patientGUI.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }
}
