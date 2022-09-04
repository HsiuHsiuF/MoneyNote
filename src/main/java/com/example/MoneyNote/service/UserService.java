package com.example.MoneyNote.service;

import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.model.UserEntityCU;

import java.util.Optional;

public interface UserService {
    public Optional<String> createUser(UserEntityCU userEntityCU);

    public UserEntity findByUserName(String username);

    public Optional<String> updatePassword(UserEntityCU userEntityCU);

    public UserEntity login(UserEntity userEntity);
}
