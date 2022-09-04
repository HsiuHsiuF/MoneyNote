package com.example.MoneyNote.service.impl;

import com.example.MoneyNote.model.UserEntity;

import com.example.MoneyNote.model.UserEntityCU;
import com.example.MoneyNote.repository.impl.UserDaoImpl;
import com.example.MoneyNote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoImpl userDaoImpl;

    public  String getMd5Password(String password ,String salt){
            //springframework的加密方式
            //md5DigestAsHex參數是Bytes，所以透過java String類將字串轉為Bytes
        String result = password + salt;
        return DigestUtils.md5DigestAsHex(result.getBytes());
    }

    //根據用戶名稱username查詢數據
    @Override
    public UserEntity findByUserName(String username) {
        return userDaoImpl.findByUsername(username);
    }

    //註冊
    @Override
    public Optional<String> createUser(UserEntityCU userEntityCU) {
        // 驗證欄位是否填寫及格式
        if(!userEntityCU.getPassword().equals(userEntityCU.getCheckPassword())){
            return Optional.of("兩次輸入密碼不相符");
        }

        //如果查詢結果不為null，代表有重複名稱
        UserEntity username =findByUserName(userEntityCU.getUsername());
        if(username != null){
            return Optional.of("該帳號已被使用");
        }

        // 產生鹽值
        String salt = UUID.randomUUID().toString().replaceAll("-","");;

        // 密碼加密
        String md5Password = getMd5Password(userEntityCU.getPassword(),salt);

        // 新增UserEntity資料
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userEntityCU.getName());
        userEntity.setUsername(userEntityCU.getUsername());
        userEntity.setPassword(md5Password);
        userEntity.setSalt(salt);
        userEntity.setCreated_time(date);
        userEntity.setModified_time(date);
        Integer result = userDaoImpl.save(userEntity);
        if(result == 0) return Optional.of("新增會員資料時發生錯誤");

        return Optional.empty();
    }
    //登入
    @Override
    public UserEntity login(UserEntity userEntity) {
        // 檢查帳號是否存在
        UserEntity data = findByUserName(userEntity.getUsername());
        if(data == null) return null;

        // 使用資料庫鹽值對輸入密碼進行加密
        String md5Password = getMd5Password(userEntity.getPassword(), data.getSalt());

        // 比對密碼是否相等
        if(!md5Password.equals(data.getPassword())) return null;

        return data;
    }

    //修改
    @Override
    public Optional<String> updatePassword(UserEntityCU userEntityCU) {
        if(!userEntityCU.getPassword().equals(userEntityCU.getCheckPassword())){
            return Optional.of("兩次輸入密碼不相符");
        }
        UserEntity userEntity = findByUserName(userEntityCU.getUsername());
        String md5Password = getMd5Password(userEntityCU.getPassword(), userEntity.getSalt());

        userEntity.setPassword(md5Password);
        userDaoImpl.update(userEntity);

        return Optional.empty();
    }
}