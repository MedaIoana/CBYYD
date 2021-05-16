package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static cbyyd_app.services.UserService.*;

public class deleteTreatmentsControllers implements Initializable {

    @FXML
    Text backMessage;

    @FXML
    Text deleteMessage;

    @FXML
    ListView treatment;

    @FXML
    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/manageTreatments.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void deleteHandler()
    {
        try {

            deleteMessage.setText("The treatment was deleted");
        }
        catch (Exception e)
        {
            deleteMessage.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treatment.getItems().addAll(UserService.seeTreatments(manageTreatmentsControllers.getUsernameP()));
    }
}
