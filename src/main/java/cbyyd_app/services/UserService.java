package cbyyd_app.services;

import cbyyd_app.controllers.LoginRegistrationControllers;
import cbyyd_app.exceptions.*;
import cbyyd_app.exceptions.CodeAlreadyExist;
import cbyyd_app.exceptions.CouldNotWriteUserException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;
import cbyyd_app.user.Week;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import cbyyd_app.user.User;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserService {
    static List<User> users;
    static final Path USERS_PATH = FileService.getPathToFile("config", "users.json");
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("users.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String password, String role,String code) throws UsernameAlreadyExistsException, CodeAlreadyExist {
        checkUserDoesNotAlreadyExist(username);
        if(role.equals("Doctor")) checkCodeDoesNotAlreadyExist(code);
        users.add(new User(username,encodePassword(username,password),role,code));
        persistUsers();
    }
    public static List<String> seePatients(String doctor){
        for (User user : users) {
            if (Objects.equals(doctor, user.getUsername()))
            {
                return user.getPatients();
            }
        }
        return Collections.emptyList();
    }

    public static List<String> seeTreatments(String patient)
    {
        for(User user:users)
        {
            if(Objects.equals(patient,user.getUsername()))
            {
                return user.getTreatments();
            }
        }
        return Collections.emptyList();
    }

    public static List<Week> seeSchedule(String doctor){
        for (User user : users) {
            if (Objects.equals(doctor, user.getUsername()))
            {
                return user.getSchedule();
            }
        }
        return Collections.emptyList();
    }

    public static void rememberDoctor(String patient){
        for (User user : users) {
            if (Objects.equals(patient, user.getUsername()))
            {
                user.setYourDoctor(LoginRegistrationControllers.getUsernameD());
            }
        }
    }

    public static String getMyDoctor(String patient){
        for (User user : users) {
            if (Objects.equals(patient, user.getUsername()))
            {
                return user.getYourDoctor();
            }
        }
        return "";
    }

    public static void addPatients(String patient, String doctor) throws PatientAlreadyExistsExeption, PatientDoesNotExistsAsUser {
        for (User user : users) {
            if (Objects.equals(doctor, user.getUsername()))
            {
                checkPatientExistsAsUser(patient);
                checkPatientDoesNotAlreadyExists(user.getPatients(), patient);
                rememberDoctor(patient);
                user.getPatients().add(patient);
            }
        }
        persistUsers();
    }

    public static void addTreatments(String patient,String treatment) throws PatientDoesNotExistsAsUser {
        for (User user : users) {
            if(Objects.equals(patient,user.getUsername()))
            {
                checkPatientExistsAsUser(patient);
                user.getTreatments().add(treatment);
            }
        }
        persistUsers();
    }

    public static void saveSchedule(String doctor,List<Week> schedule){
        for (User user : users) {
            if (Objects.equals(doctor, user.getUsername()))
            {
                if(user.getSchedule().size()>0) user.getSchedule().clear();
                user.setSchedule(schedule);
            }
        }
        persistUsers();
    }

    public static int manySchedule(String doctor){
        for (User user : users) {
            if (Objects.equals(doctor, user.getUsername()))
            {
                if(user.getSchedule().size()>0) return 1;
            }
        }
        return 0;
    }

    public static void editTreatments(String patient, String treatment) throws PatientDoesNotExistsAsUser
    {
        for (User user : users) {
            if(Objects.equals(patient,user.getUsername()))
            {
                checkPatientExistsAsUser(patient);
            }
        }
        persistUsers();
    }

    public static void deleteTreatment(String patient, String treatment) throws PatientDoesNotExistsAsUser
    {
        for (User user : users) {
            if(Objects.equals(patient,user.getUsername()))
            {
                checkPatientExistsAsUser(patient);
                user.getTreatments().remove(treatment);
            }
        }
        persistUsers();
    }

    public static void checkPatientExistsAsUser(String username) throws PatientDoesNotExistsAsUser {
        boolean find=false;
        for (User user : users) {
            if(user.getRole().equals("Patient")) {
                if (Objects.equals(username, user.getUsername())) {
                    find = true;
                    break;
                }
            }
        }
        if(!find) throw new PatientDoesNotExistsAsUser(username);
    }

    public static void checkPatientDoesNotAlreadyExists(List<String> patient,String username) throws PatientAlreadyExistsExeption {
        for (int i = 0; i < patient.size(); i++) {
            if (patient.get(i).equals(username))
                throw new PatientAlreadyExistsExeption(username);
        }
    }

    public static void deletePatients(String patient, String doctor) throws ThePatientDoesNotExistsExeption{
        for (User user : users) {
            if (Objects.equals(doctor, user.getUsername()))
            {
                checkPatientExistsInDoctorList(user.getPatients(), patient);
                user.getPatients().remove(patient);
            }
        }
        persistUsers();
    }

    public static void checkPatientExistsInDoctorList(List<String> patient,String username) throws ThePatientDoesNotExistsExeption{
        boolean find=false;
        for(int i=0;i<patient.size();i++) {
            if (patient.get(i).equals(username)){
                find=true;
                break;
            }
        }
        if(!find) throw new ThePatientDoesNotExistsExeption(username);
    }

    public static void checkCodeDoesNotAlreadyExist(String code) throws CodeAlreadyExist {
        for (User user : users) {
            if (Objects.equals(code, user.getCode()))
                throw new CodeAlreadyExist(code);
        }
    }

    public static void checkUsernameAndPassword(String username, String password) throws  WrongUsernamePasswordException{
        boolean find=false;
        for (User user: users) {
            if(Objects.equals(username,user.getUsername()) && Objects.equals(encodePassword(username,password),user.getPassword())){
                find=true;
                break;
            }
        }
        if(!find) throw new  WrongUsernamePasswordException(username,password);
    }

    static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUserException();
        }
    }

    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static List<User> getUsers() {
        return users;
    }
}
