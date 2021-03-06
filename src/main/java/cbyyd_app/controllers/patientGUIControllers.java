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
    Text mySchedule;

    @FXML
    Text makeAppointmentMessage;

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
        try {
            seeTreatmentsMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/seeTreatments.fxml")));
        } catch (Exception e) {
            seeTreatmentsMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void AppointmentHandler() {
        try {
            makeAppointmentMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/makeAppointment.fxml")));
        } catch (Exception e) {
            makeAppointmentMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void ScheduleHandler() {
        try {
            mySchedule.getScene().setRoot(FXMLLoader.load(getClass().getResource("/createPatientSchedule.fxml")));
        } catch (Exception e) {
            mySchedule.setText(e.getMessage());
        }
    }

    public void logoutHandler() {
        try {
            logoutMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/login.fxml")));
        } catch (Exception e) {
            logoutMessage.setText(e.getMessage());
        }
    }
}
