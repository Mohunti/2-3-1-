package web.service;
import web.models.User;
import java.util.List;

public interface UserService {
    void add(User user);
    void delete(int id);
    List<User> listUsers();
    User get(int id);
    void update(int id, User user);
}