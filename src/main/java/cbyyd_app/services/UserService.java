package cbyyd_app.services;

import cbyyd_app.exceptions.WrongUsernamePasswordException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import cbyyd_app.exceptions.UsernameAlreadyExistsException;
import cbyyd_app.user.User;

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

    public static void addUser(String username, String password, String role, String code) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username, encodePassword(username, password), role, code));
    }

    public static void loginUser(String username, String password, String role) throws WrongUsernamePasswordException {

    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException
    {
        for (User user : users)
        {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void CheckPasswordIsCorrect(String password) throws WrongUsernamePasswordException
    {
        for (User user : users)
        {
            if (!Objects.equals(password, user.getPassword()))
                throw new WrongUsernamePasswordException();
        }
    }

    public static void CheckUsernameIsCorrect(String username) throws WrongUsernamePasswordException
    {
        for (User user : users)
        {
            if (!Objects.equals(username, user.getUsername()))
                throw new WrongUsernamePasswordException();
        }
    }
}
