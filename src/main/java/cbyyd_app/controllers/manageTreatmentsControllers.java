package cbyyd_app.controllers;

import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;
import cbyyd_app.exceptions.patientNotExist;
import cbyyd_app.services.UserService;
import cbyyd_app.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.util.Objects;

public class manageTreatmentsControllers {

    @FXML
    private TextField username;

    @FXML
    public void username() {
        try{
            UserService.checkUserExist(username);
        }catch (patientNotExist e){
            e.printStackTrace();
        }
    }
}
