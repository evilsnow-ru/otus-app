package ru.evilsnow.otususers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    private final UsersRepository usersRepository;
    private final Mapper<UserEntity, User> usersMapper;

    public UsersServiceImpl(UsersRepository usersRepository, Mapper<UserEntity, User> usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    public Page<User> getUsers(Pageable pageable) throws DataAccessException {
        return usersRepository.findAll(pageable).map(usersMapper::mapTo);
    }

    @Override
    public Optional<User> getUser(long userId) throws DataAccessException {
        return usersRepository.findById(userId).map(usersMapper::mapTo);
    }

    @Override
    public User createUser(User user) throws DataAccessException {
        log.info("Save user: {}", user);
        UserEntity entity = usersRepository.save(usersMapper.mapFrom(user));
        user.setId(entity.getId());
        return user;
    }

    @Override
    @Transactional
    public User updateUser(long userId, User user) throws DataAccessException {
        UserEntity entity = usersRepository.getById(userId);
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        user.setId(userId);
        user.setUserName(entity.getUserName());
        return user;
    }

    @Override
    public void deleteUser(long userId) throws DataAccessException {
        log.info("Delete user with id = {}", userId);
        usersRepository.deleteById(userId);
    }

}
