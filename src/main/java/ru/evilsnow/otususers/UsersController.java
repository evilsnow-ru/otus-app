package ru.evilsnow.otususers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UsersController {

    private static final ResponseEntity<User> USER_NOT_FOUND_RESPONSE =
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long userId) {
        if (userId < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return usersService
                .getUser(userId)
                .map(ResponseEntity::ok)
                .orElse(USER_NOT_FOUND_RESPONSE);
    }

    @GetMapping
    public Page<User> getUsers(Pageable pageable) {
        return usersService.getUsers(pageable);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user.getId() != null) {
            user.setId(null);
        }
        User savedUser = usersService.createUser(user);
        String createdPath =
                MvcUriComponentsBuilder
                        .fromController(getClass())
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toString();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", createdPath)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long userId, @RequestBody User user) {
        if (userId < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(usersService.updateUser(userId, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long userId) {
        if (userId < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            usersService.deleteUser(userId);
            return ResponseEntity.ok(null);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
