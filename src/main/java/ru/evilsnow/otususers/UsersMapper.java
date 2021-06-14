package ru.evilsnow.otususers;

import org.springframework.stereotype.Component;

@Component
public class UsersMapper implements Mapper<UserEntity, User> {

    @Override
    public User mapTo(UserEntity entity) {
        var user = new User();
        user.setId(entity.getId());
        user.setUserName(entity.getUserName());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPhone(entity.getPhone());
        return user;
    }

    @Override
    public UserEntity mapFrom(User user) {
        var entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUserName(user.getUserName());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        return entity;
    }

}
