package cbyyd_app.controllers;

import cbyyd_app.exceptions.PatientAlreadyExistsExeption;
import cbyyd_app.exceptions.ThePatientDoesNotExistsExeption;
import cbyyd_app.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class managingpatientControllers {

    @FXML
    Text backMessage;
    @FXML
    private TextField usernameField;
    @FXML
    Text addingMessage;
    @FXML
    Text deletingMessage;

    @FXML
    public void addHandler() {
        try {
            UserService.addPatients(usernameField.getText(),LoginRegistrationControllers.getUsernameD());
            addingMessage.setText("The patient was added successfully!");
        } catch (PatientAlreadyExistsExeption e) {
            addingMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void deleteHandler() {
        try {
            UserService.deletePatients(usernameField.getText(),LoginRegistrationControllers.getUsernameD());
            deletingMessage.setText("The patient was deleted successfully!");
        } catch (ThePatientDoesNotExistsExeption e) {
            deletingMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void backHAndler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/doctorGUI.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }
}
