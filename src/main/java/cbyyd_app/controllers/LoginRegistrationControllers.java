package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import cbyyd_app.services.UserService;
import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;

public class LoginRegistrationControllers {
    @FXML
    Text registrationMessage;
    @FXML
    Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox<String> role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Patient", "Doctor");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue(),codeField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLoginAction() {
        try {
            UserService.loginUser(usernameField.getText(), passwordField.getText(), role.getValue());
            loginMessage.setText("Login!");
        } catch (WrongUsernamePasswordException e) {
            loginMessage.setText(e.getMessage());
        }
    }
}
