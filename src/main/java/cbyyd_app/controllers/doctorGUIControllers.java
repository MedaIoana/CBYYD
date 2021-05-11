package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class doctorGUIControllers {

    @FXML
    Text managepatientMessage;

    public void managePatientsHandler() {
        try {
            managepatientMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/managingpatientGUI.fxml")));
        } catch (Exception e) {
            managepatientMessage.setText(e.getMessage());
        }
    }
}
