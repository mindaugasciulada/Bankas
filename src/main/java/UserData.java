import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;


public class UserData {
    private Map<Integer, User> users = new HashMap();

    public UserData() {

        List<User> usersArray = Arrays.asList(
                new User(1, "Tomas", "Kalinauskas", 1578),
                new User(2, "Martynas", "Buinauskas", 4540),
                new User(3, "Alvydaa", "Ramanauskaite", 6585)
        );

        usersArray.forEach(user -> {this.users.put(user.getId(), user);
        });
    }

    public void createUser(User user) {
        user.setId(users.size() + 1);
        users.put(user.getId(), user);
    }
    public void updateUser(int id, User user) {
        user.setId(id);
        users.put(id, user);
    }
    public void deleteUser(int id) {
        users.remove(id);
    }

    public User get(int id) {
        return users.get(id);
    }

    public List<User> getAll() {
        return users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}

