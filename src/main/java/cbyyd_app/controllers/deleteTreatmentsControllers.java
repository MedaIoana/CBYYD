package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
            treatment.getItems().remove(treatment.getSelectionModel().getSelectedItem());
            ArrayList<String> t=new ArrayList<>(treatment.getItems());
           // System.out.println((String) treatment.getSelectionModel().getSelectedItem());
            UserService.deleteTreatment(manageTreatmentsControllers.getUsernameP(), t);
            System.out.println(t);
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
