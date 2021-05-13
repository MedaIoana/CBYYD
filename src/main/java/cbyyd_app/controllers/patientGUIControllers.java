package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class patientGUIControllers {

    @FXML
    Text seeTreatmentsMessage;

    @FXML
    Text seeDoctorScheduleMessage;

    @FXML
    Text logoutMessage;

    @FXML
    public void DoctorScheduleHandler() {
        try {
            seeDoctorScheduleMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/seeDoctorSchedule.fxml")));
        } catch (Exception e) {
            seeDoctorScheduleMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void TreatementsTestsHandler() {

    }

    @FXML
    public void AppointmentHandler() {

    }

    @FXML
    public void ScheduleHandler() {

    }

    public void logoutHandler() {
        try {
            logoutMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/login.fxml")));
        } catch (Exception e) {
            logoutMessage.setText(e.getMessage());
        }
    }
}
