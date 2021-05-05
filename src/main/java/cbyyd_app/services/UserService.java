package cbyyd_app.services;

import cbyyd_app.exceptions.CodeAlreadyExist;
import cbyyd_app.exceptions.CouldNotWriteUserException;
import cbyyd_app.exceptions.WrongUsernamePasswordException;
import cbyyd_app.exceptions.CodeAlreadyExist;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.user.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static List<User> users;
    private static final Path USERS_PATH = FileService.getPathToFile("config", "users.json");

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
        checkCodeDoesNotAlreadyExist(code);
        users.add(new User(username,encodePassword(username,password),role,code));
        persistUsers();
    }
    public static void checkCodeDoesNotAlreadyExist(String code) throws CodeAlreadyExist {
        for (User user : users) {
            if (Objects.equals(code, user.getCode()))
                throw new CodeAlreadyExist(code);
        }
    }

    public static void loginUser(String username, String password) throws WrongUsernamePasswordException {
        checkUsernameAndPassword(username,password);
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
