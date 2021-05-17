package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import cbyyd_app.user.Week;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class makeAppointmentControllers implements Initializable {
    @FXML
    Text backMessage;
    @FXML
    private TableView<Week> tableView;
    @FXML
    private TableColumn<Week,String> hoursCollumn;
    @FXML
    private TableColumn<Week,String> MondayCollumn;
    @FXML
    private TableColumn<Week,String> TuesdayCollumn;
    @FXML
    private TableColumn<Week,String> WednesdayCollumn;
    @FXML
    private TableColumn<Week,String> ThursdayCollumn;
    @FXML
    private TableColumn<Week,String> FridayCollumn;
    @FXML
    private ChoiceBox<String> hour;
    @FXML
    private ChoiceBox<String> day;

    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/patientGUI.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hour.getItems().addAll("8-9","9-10","10-11","11-12","12-13","13-14","14-15","15-16","16-17","17-18");
        day.getItems().addAll("Monday","Tuesday","Wednesday","Thursday","Friday");
        hoursCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Hours"));
        MondayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Monday"));
        TuesdayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Tuesday"));
        WednesdayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Wednesday"));
        ThursdayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Thursday"));
        FridayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Friday"));
        tableView.getItems().addAll(UserService.seeSchedule(UserService.getMyDoctor(LoginRegistrationControllers.getUsernameP())));
    }
}
