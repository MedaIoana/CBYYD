package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import cbyyd_app.services.UserService;
import cbyyd_app.exceptions.UsernameAlreadyExists;

public class LoginRegistrationControllers {
    @FXML
    private Text registrationMessage;
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Patient", "Doctor");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(),codeField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExists e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLoginAction() {
        try {
            UserService.loginUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            loginMessage.setText("Login!");
        } catch (WrongUsernamePassword e) {
            loginMessage.setText(e.getMessage());
        }
    }
}
