package cbyyd_app.services;

import cbyyd_app.exceptions.*;
import cbyyd_app.user.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @BeforeClass
    public static void setupClass() {
        FileService.APPLICATION_FOLDER = ".test-cbyyd";
        FileService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.users);
        assertEquals(0, UserService.users.size());
    }

    @Test
    public void testAddOneUser() throws Exception, CodeAlreadyExist {
        UserService.loadUsersFromFile();
        UserService.addUser("test","testpass","Patient","");
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }

    @Test
    public void testAddTwoUsers() throws Exception, CodeAlreadyExist {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "Patient","");
        UserService.addUser("test2", "testPass2", "Doctor","5336");
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }

    @Test
    public void testAddOneUserIsPersisted() throws Exception, CodeAlreadyExist {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "Patient","");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception, CodeAlreadyExist {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "Doctor","8669");
        UserService.addUser("test2", "testPass2", "Doctor","4723");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testCheckDoctorsCode() throws IOException, CodeAlreadyExist, UsernameAlreadyExistsException {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "Doctor", "8669");
        try {
            UserService.addUser("test2", "testPass2", "Doctor", "8669");
        }catch (CodeAlreadyExist e){
            assertEquals("An account with this code 8669 already exists!",e.getMessage());
        }
    }

    @Test
    public void testLoadWithUserPAsswordRole() throws IOException, CodeAlreadyExist, UsernameAlreadyExistsException {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "Doctor","8669");
       try {
           UserService.checkUsernameAndPassword("test1", "testPass", "Doctor");
       }catch (WrongUsernamePasswordException e){
        assertEquals("The username or the password are incorrect, or the role is misleading",e.getMessage());
       }

    }

    @Test
    public void testAddinDoctorListExistingPatient() throws IOException, CodeAlreadyExist, UsernameAlreadyExistsException {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "Doctor","8669");
        UserService.addUser("test", "testPass", "Patient","");
        try {
            UserService.addPatients("test","test1");
            UserService.addPatients("test","test1");
        }catch (PatientAlreadyExistsExeption | PatientDoesNotExistsAsUser e){
            assertEquals("Patient test is already in the list!",e.getMessage());
        }
    }

    @Test
    public void testAddinDoctorListInexistingPatient() throws IOException, CodeAlreadyExist, UsernameAlreadyExistsException {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "Doctor","8669");
        UserService.addUser("test", "testPass", "Patient","");
        try {
            UserService.addPatients("test","test1");
            UserService.addPatients("test3","test1");
        }catch (PatientAlreadyExistsExeption | PatientDoesNotExistsAsUser e){
            assertEquals("Patient test3 does not exists as a user!",e.getMessage());
        }
    }

    @Test
    public void testAddTreatmentForPatient() throws IOException, CodeAlreadyExist, UsernameAlreadyExistsException {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "Patient","");
        try {
            UserService.addTreatments("test","treatmenttest");
        }catch (PatientDoesNotExistsAsUser e){
            assertEquals("Patient %s does not exists as a user!",e.getMessage());
        }
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }
}
