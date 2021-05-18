package cbyyd_app.controllers;

import cbyyd_app.services.FileService;
import cbyyd_app.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class LoginRegistrationControllersTest extends ApplicationTest{

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD ="testPassword";
    public static final String TEST_CHIOCE="Patient";
    public static final String TEST_CODE="5366";
    private LoginRegistrationControllers controller;

    @BeforeClass
    public static void setupClass() throws Exception{
        FileService.APPLICATION_FOLDER=".test-cbyyd";
        FileService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception{
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();
        ObservableList<String> cursors = FXCollections.observableArrayList("Patient","Doctor");

        controller=new LoginRegistrationControllers();
        controller.usernameField=new TextField();
        controller.passwordField=new PasswordField();
        controller.role=new ChoiceBox();
        controller.loginMessage=new Text();
        controller.registrationMessage=new Text();
        controller.codeField=new TextField();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
        controller.role.setItems(cursors);
        controller.role.setValue(TEST_CHIOCE);
        controller.codeField.setText(TEST_CODE);
    }

    @Test
    public void testHandleAddUserActionCode(){
        controller.handleRegisterAction();
        assertEquals(1,UserService.getUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }

    @Test
    public void testAddSameUserTwice(){
        controller.handleRegisterAction();
        controller.handleRegisterAction();
        assertEquals("An account with the username " + TEST_USER + " already exists!",controller.registrationMessage.getText());
    }

}
