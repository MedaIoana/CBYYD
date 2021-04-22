package cbyyd_app.controllers;

import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import cbyyd_app.services.UserService;

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
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLoginAction() {
        try {
            UserService.loginUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            loginMessage.setText("Login!");
        } catch (WrongUsernamePasswordException e) {
            loginMessage.setText(e.getMessage());
        }
    }
}
