package br.com.letscode.zoo.controller;

import br.com.letscode.zoo.dto.UserDTO;
import br.com.letscode.zoo.model.User;
import br.com.letscode.zoo.repository.UserRepository;
import br.com.letscode.zoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getUserDetails() {
        String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> opUser = userService.findByUsername(userName);
        if (opUser.isPresent()) {
            User user = opUser.get();
            return ResponseEntity.ok(new UserDTO(
                    user.getUid(), user.getUsername(),
                    user.getName(), user.getEmail()
            ));
        }
        return ResponseEntity.notFound().build();
    }
}
