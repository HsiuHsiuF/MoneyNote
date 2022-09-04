package com.example.MoneyNote.repository;


import com.example.MoneyNote.model.UserEntity;



public interface UserDao  {
    public UserEntity findByUsername(String username);

    public UserEntity findById(Integer id);

    public Integer save(UserEntity userEntity);

    public Integer update(UserEntity userEntity);

}

