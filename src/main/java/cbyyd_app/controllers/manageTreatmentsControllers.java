package cbyyd_app.controllers;

import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;
import cbyyd_app.exceptions.patientNotExist;
import cbyyd_app.services.UserService;
import cbyyd_app.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Objects;

public class manageTreatmentsControllers {

    private static String usernameP;

    @FXML
    private TextField username;
    @FXML
    Text addMessage;
    @FXML
    Text editMessage;
    @ FXML
    Text backMessage;
    @FXML
    Text deleteMessage;

    public static String getUsernameP() {
        return usernameP;
    }

    @FXML
    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/doctorGUI.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void addHandler()
    {
        try{
            usernameP=username.getText();
            addMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/addTreatments.fxml")));
        }
        catch (Exception e)
        {
            addMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void editHandler()
    {
        try
        {
            usernameP=username.getText();
            editMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/editTreatments.fxml")));
        }
        catch (Exception e)
        {
            editMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void deleteHandler()
    {
        try
        {
            usernameP=username.getText();
            deleteMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/deleteTreatments.fxml")));
        }
        catch (Exception e)
        {
            deleteMessage.setText(e.getMessage());
        }
    }
}
