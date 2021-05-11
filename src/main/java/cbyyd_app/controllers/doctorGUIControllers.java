package cbyyd_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class doctorGUIControllers {

    @FXML
    Text managepatientMessage;

    @FXML
    Text manageTreatmentsMessage;

    @FXML
    Text seePatientsMessage;

    @FXML
    Text createScheduleMessage;

    public void managePatientsHandler() {
        try {
            managepatientMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/managingpatientGUI.fxml")));
        } catch (Exception e) {
            managepatientMessage.setText(e.getMessage());
        }
    }

    public void manageTreatmentsHandler()
    {
        try
        {
            manageTreatmentsMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/manageTreatments.fxml")));
        }
        catch (Exception e)
        {
            manageTreatmentsMessage.setText(e.getMessage());
        }
    }

    public void seePatientsHandler()
    {
        try
        {
            seePatientsMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/seepatient.fxml")));
        }
        catch (Exception e)
        {
            seePatientsMessage.setText(e.getMessage());
        }
    }

    public void createScheduleHandler()
    {
        try
        {
            createScheduleMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/createDoctorSchedule.fxml")));
        }
        catch (Exception e)
        {
            createScheduleMessage.setText(e.getMessage());
        }
    }
}
