package com.example.MoneyNote.repository.impl;

import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate jdbcNameTemplate;

    @Override
    public UserEntity findByUsername(String username){
        String sql = " SELECT * FROM user WHERE username = ?";
        List<UserEntity> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserEntity>(UserEntity.class)/*資料庫欄位自動對應實體變數*/, new Object[] { username });
        if(result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    };

    public UserEntity findById(Integer id){
        String sql = " SELECT * FROM user WHERE id = ?";
        List<UserEntity> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserEntity>(UserEntity.class)/*資料庫欄位自動對應實體變數*/, new Object[] { id });
        if(result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Integer save(UserEntity userEntity){
        String sql = " INSERT INTO user (USERNAME,PASSWORD,SALT,CREATE_TIME,UPDATE_TIME) VALUE (:username, :password, :salt, NOW(), NOW())";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(userEntity);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcNameTemplate.update(sql, paramSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer update(UserEntity userEntity){
        String sql = " UPDATE user SET PASSWORD = :password, MODIFIED_TIME = NOW() WHERE ID = :id";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(userEntity);
        return jdbcNameTemplate.update(sql, paramSource);
    }
}
