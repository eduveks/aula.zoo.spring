package br.com.letscode.zoo.controller;

import br.com.letscode.zoo.dto.UserLoginDTO;
import br.com.letscode.zoo.dto.UserRegisterDTO;
import br.com.letscode.zoo.model.User;
import br.com.letscode.zoo.security.JWTUtil;
import br.com.letscode.zoo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        String encodedPass = passwordEncoder.encode(userRegisterDTO.getPassword());
        userRegisterDTO.setPassword(encodedPass);

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        userService.create(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return Collections.singletonMap("jwt-token",token);
    }

    @PostMapping("/login")
    public Map<String,Object> login(@Valid  @RequestBody UserLoginDTO userLoginDTO) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(userLoginDTO.getUsername());
            return Collections.singletonMap("jwt-token",token);
        } catch(AuthenticationException authExc) {
            throw new RuntimeException("Invalid username/password.");
        }

    }

}
