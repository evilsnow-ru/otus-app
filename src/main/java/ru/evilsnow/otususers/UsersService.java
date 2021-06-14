package ru.evilsnow.otususers;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsersService {

    Page<User> getUsers(Pageable pageable) throws DataAccessException;
    Optional<User> getUser(long userId) throws DataAccessException;
    User createUser(User user) throws DataAccessException;
    User updateUser(long userId, User user) throws DataAccessException;
    void deleteUser(long userId) throws DataAccessException;

}
