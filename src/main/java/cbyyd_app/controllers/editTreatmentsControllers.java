package cbyyd_app.controllers;

import cbyyd_app.services.UserService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class editTreatmentsControllers implements Initializable {

    private List<String> edit;

    @FXML
    Text backMessage;

    @FXML
    Text editMessage;

    @FXML
    ListView<String> treatment;

    @FXML
    public void backHandler() {
        try {
            backMessage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/manageTreatments.fxml")));
        } catch (Exception e) {
            backMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void editHandler()
    {
       try{
            UserService.editTreatments(manageTreatmentsControllers.getUsernameP(), edit);
        }
        catch (Exception e)
        {
            editMessage.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treatment.getItems().addAll(UserService.seeTreatments(manageTreatmentsControllers.getUsernameP()));
        treatment.setEditable(true);
        treatment.setCellFactory(TextFieldListCell.forListView());

        treatment.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
                edit=treatment.getItems();
                treatment.getItems().set(t.getIndex(), t.getNewValue());
            }

        });
    }
}
