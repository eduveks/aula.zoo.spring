package br.com.letscode.zoo.service;

import br.com.letscode.zoo.exception.NotFoundException;
import br.com.letscode.zoo.model.User;
import br.com.letscode.zoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(User user) {
        user.setUid(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
