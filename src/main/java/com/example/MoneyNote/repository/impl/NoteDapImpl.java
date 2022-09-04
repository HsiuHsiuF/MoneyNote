package com.example.MoneyNote.repository.impl;

import com.example.MoneyNote.model.NoteEntity;
import com.example.MoneyNote.repository.NoteDao;
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
public class NoteDapImpl implements NoteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate jdbcNameTemplate;

    @Override
    public List<NoteEntity> findByUser_idAndMonth(Integer user_id, Integer month){
        String sql = " SELECT * FROM money WHERE USER_ID = ? AND MONTH(date) =?";
        List<NoteEntity> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NoteEntity>(NoteEntity.class)/*資料庫欄位自動對應實體變數*/, user_id, month);
        if(result != null) {
            return result;
        }
        return null;
    };


    @Override
    public NoteEntity findById(Integer id){
        String sql = " SELECT * FROM money WHERE ID = ?";
        List<NoteEntity> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<NoteEntity>(NoteEntity.class)/*資料庫欄位自動對應實體變數*/, new Object[] { id });
        if(result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    };

    @Override
    public Integer save(NoteEntity noteEntity){
        String sql = " INSERT INTO money (DATE,NAME,PAY,BILLITEM,USER_ID) VALUE (:date, :name, :pay, :billitem, :user_id)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noteEntity);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcNameTemplate.update(sql, paramSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer deleteById(Integer id){
        String sql = " DELETE FROM money WHERE ID = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Iterable<NoteEntity> findAll(){
        String sql = " SELECT * FROM money";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NoteEntity>(NoteEntity.class));
    }
}
