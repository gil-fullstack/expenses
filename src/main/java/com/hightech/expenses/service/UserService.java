package com.hightech.expenses.service;

import com.hightech.expenses.domain.User;
import com.hightech.expenses.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User.UserRequest req) {
        userRepository.findByEmail(req.email()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        });
        User u = User.builder()
                .nome(req.nome())
                .email(req.email())
                .senha(req.senha())
                .build();
        return userRepository.save(u);
    }

    @Transactional(readOnly = true)
    public List<User> list() { return userRepository.findAll(); }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public User update(Long id, User.UserRequest req) {
        User u = get(id);
        u.setNome(req.nome());
        u.setEmail(req.email());
        if (req.senha() != null && !req.senha().isBlank()) {
            u.setSenha(req.senha());
        }
        return userRepository.save(u);
    }

    public void delete(Long id) {
        User u = get(id);
        u.setAtivo(false);
        userRepository.save(u);
    }

    public User.UserResponse toResponse(User u) {
        return new User.UserResponse(u.getId(), u.getNome(), u.getEmail());
    }
}
