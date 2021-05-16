package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static cbyyd_app.services.UserService.addTreatments;

public class addTreatmentsControllers {

    @FXML
    Text backMessage;

    @FXML
    Text addMessage;

    @FXML
    TextArea treatment;

    @FXML
    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/manageTreatments.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void addHandler()
    {
        try {
            addTreatments(manageTreatmentsControllers.getUsernameP(),treatment.getText());
            addMessage.setText("The treatment was added");
        }
        catch (Exception e)
        {
            addMessage.setText(e.getMessage());
        }
    }

}
