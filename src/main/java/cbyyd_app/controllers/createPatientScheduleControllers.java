package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import cbyyd_app.user.Week;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class createPatientScheduleControllers implements Initializable {

    @FXML
    Text saveMessage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int many=UserService.manySchedule(LoginRegistrationControllers.getUsernameD());
        if(many==0) loadSchedule();
        else tableView.getItems().addAll(UserService.seeSchedule(LoginRegistrationControllers.getUsernameD()));
        initTable();
    }

    private void initTable(){
        initCols();
    }

    private void initCols(){
        hoursCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Hours"));
        MondayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Monday"));
        TuesdayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Tuesday"));
        WednesdayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Wednesday"));
        ThursdayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Thursday"));
        FridayCollumn.setCellValueFactory(new PropertyValueFactory<Week,String>("Friday"));

        editableCols();
    }

    private void editableCols() {
        hoursCollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hoursCollumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setHours(e.getNewValue());
        });

        MondayCollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        MondayCollumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMonday(e.getNewValue());
        });

        TuesdayCollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        TuesdayCollumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTuesday(e.getNewValue());
        });

        WednesdayCollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        WednesdayCollumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setWednesday(e.getNewValue());
        });

        ThursdayCollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ThursdayCollumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setThursday(e.getNewValue());
        });

        FridayCollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        FridayCollumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFriday(e.getNewValue());
        });

        tableView.setEditable(true);

    }

    private void loadSchedule(){
        ObservableList<Week> week_table=FXCollections.observableArrayList();

        for (int i=0; i<24;i++){
            week_table.add(new Week(i + "-" + (i+1) ,"","","","",""));
        }
        tableView.setItems(week_table);
    }

    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/patientGUI.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    public void saveHandler() {
        try{
            UserService.saveSchedule(LoginRegistrationControllers.getUsernameD(), tableView.getItems());
            saveMessage.setText("The schedule was saved successfully!");
        } catch (Exception e) {
            saveMessage.setText(e.getMessage());
        }
    }
}
