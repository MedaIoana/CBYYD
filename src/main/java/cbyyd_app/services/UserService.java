package cbyyd_app.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import cbyyd_app.exceptions.UsernameAlreadyExists;
import cbyyd_app.user.User;
import cbyyd_app.services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;


public class UserService {
    private static List<User> users;
    private static final Path USERS_PATH = FileService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String password, String role,String code) throws UsernameAlreadyExists{
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username,encodePassword(username,password),role,code));
    }

    public static void loginUser(String username, String password, String role) throws WrongUsernamePassword {

    }

    static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExists {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExists(username);
        }
    }
}
