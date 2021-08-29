package web.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public void update(int id, User user) {
        userDao.update(id, user);
    }
}