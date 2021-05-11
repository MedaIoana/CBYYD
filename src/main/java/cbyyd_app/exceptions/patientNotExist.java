package cbyyd_app.exceptions;

import javafx.scene.control.TextField;

import java.awt.*;

public class patientNotExist extends Exception{

    public patientNotExist(TextField username)
    {
        super(String.format("Patient %s does not exist",username));

    }
}
