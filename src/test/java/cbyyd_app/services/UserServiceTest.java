package cbyyd_app.services;

import cbyyd_app.exceptions.CodeAlreadyExist;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class UserServiceTest {

    @BeforeClass
    public static void setupClass() {
        FileService.APPLICATION_FOLDER = ".test-registration-example";
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
    public void testPasswordEncoding() {
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }
}
