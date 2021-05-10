package cbyyd_app.services;

import cbyyd_app.exceptions.PatientAlreadyExistsExeption;
import cbyyd_app.user.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class DoctorService extends UserService {
    private static List<User> patients;
    private static final Path USERS_PATH = FileService.getPathToFile("config", "patients.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("patients.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        patients = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addPatient(String username) throws PatientAlreadyExistsExeption {
        checkPatientDoesNotAlreadyExists(username);
        patients.add(new User(username));
        persistUsers();
    }

    public static void checkPatientDoesNotAlreadyExists(String username) throws PatientAlreadyExistsExeption{
        for (User patient : patients) {
            if (Objects.equals(username, patient.getUsername()))
                throw new PatientAlreadyExistsExeption(username);
        }
    }
}
