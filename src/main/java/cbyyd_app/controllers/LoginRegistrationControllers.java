package cbyyd_app.controllers;

import cbyyd_app.exceptions.CodeAlreadyExist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import cbyyd_app.services.UserService;
import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;

public class LoginRegistrationControllers {

    private static String usernameD;
    private static String usernameP;

    @FXML
    Text registrationMessage;
    @FXML
    public Text loginMessage;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField codeField;
    @FXML
    TextField usernameField;
    @FXML
    ChoiceBox<String> role;

    public static String getUsernameP() { return usernameP; }

    public static void setUsernameP(String usernameP) { LoginRegistrationControllers.usernameP = usernameP; }

    @FXML
    public void initialize() {
        role.getItems().addAll("Patient", "Doctor");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            boolean empty=role.getSelectionModel().isEmpty();
            if(empty) registrationMessage.setText("You must select a mode!");
            else {
                UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue(), codeField.getText());
                registrationMessage.setText("Account created successfully!");
            }
        } catch (UsernameAlreadyExistsException | CodeAlreadyExist e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLoginAction() {
        try {
            UserService.checkUsernameAndPassword(usernameField.getText(),passwordField.getText(),role.getValue());
            if (role.getValue().equals("Patient")) {
                try {
                    usernameP=usernameField.getText();
                    loginMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/patientGUI.fxml")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (role.getValue().equals("Doctor")) {
                try {
                    usernameD=usernameField.getText();
                    loginMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/doctorGUI.fxml")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (WrongUsernamePasswordException e){
            loginMessage.setText(e.getMessage());
        }
    }

    public static String getUsernameD() {
        return usernameD;
    }

}
