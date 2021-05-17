package cbyyd_app.controllers;

import cbyyd_app.exceptions.ThePatientDoesNotExistsExeption;
import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;
import cbyyd_app.exceptions.patientNotExist;
import cbyyd_app.services.UserService;
import cbyyd_app.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
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
            UserService.checkPatientExistsInDoctorList(UserService.seePatients(LoginRegistrationControllers.getUsernameD()),usernameP);
            addMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/addTreatments.fxml")));
        }
        catch (ThePatientDoesNotExistsExeption e)
        {
            addMessage.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editHandler()
    {
        try
        {
            usernameP=username.getText();
            UserService.checkPatientExistsInDoctorList(UserService.seePatients(LoginRegistrationControllers.getUsernameD()),usernameP);
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
            UserService.checkPatientExistsInDoctorList(UserService.seePatients(LoginRegistrationControllers.getUsernameD()),usernameP);
            deleteMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/deleteTreatments.fxml")));
        }
        catch (Exception e)
        {
            deleteMessage.setText(e.getMessage());
        }
    }
}
