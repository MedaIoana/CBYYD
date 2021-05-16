package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class seeDoctorScheduleControllers {
    @FXML
    Text backMessage;

    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/patientGUI.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }
}
