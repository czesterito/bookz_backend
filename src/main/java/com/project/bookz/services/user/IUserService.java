package com.project.bookz.services.user;

import com.project.bookz.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAllUsers();

    Optional<User> findUserById(Integer id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByName(String name);

    void deleteUser(Integer id);

    User addNewUser(User newUser);

    User updateUser(User oldUser, User newUser);

    boolean checkIfEmailIsTaken(String email);

    boolean checkIfNameIsTaken(String username);
}
