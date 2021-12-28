package com.project.bookz.services.user;

import com.project.bookz.models.User;
import com.project.bookz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;


    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addNewUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User oldUser, User newUser) {
        oldUser = newUser;
        return userRepository.save(oldUser);
    }

    @Override
    public boolean checkIfEmailIsTaken(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Override
    public boolean checkIfNameIsTaken(String username) {
        return findUserByName(username).isPresent();
    }
}
