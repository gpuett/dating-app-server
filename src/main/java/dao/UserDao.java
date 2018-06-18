package dao;

import models.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    List<User> getAll();

    User findById(int id);

    void update(int id, String name, String bio, int age, String orientation, String imageUrl, String interests);

    void deleteById(int id);

    void clearAll();
}
