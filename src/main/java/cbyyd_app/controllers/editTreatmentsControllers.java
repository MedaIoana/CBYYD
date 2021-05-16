package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class editTreatmentsControllers {

    @FXML
    Text backMessage;

    @FXML
    Text editMessage;

    @FXML
    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/manageTreatments.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void editHandler()
    {
        try {
            editMessage.setText("The treatment was edited");
        }
        catch (Exception e)
        {
            editMessage.setText(e.getMessage());
        }
    }
}
