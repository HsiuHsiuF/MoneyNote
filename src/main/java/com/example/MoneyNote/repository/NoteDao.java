package com.example.MoneyNote.repository;
import com.example.MoneyNote.model.NoteEntity;

import java.util.List;


public interface NoteDao {

    public List<NoteEntity> findByUser_idAndMonth(Integer user_id, Integer month);
    public Integer save(NoteEntity noteEntity);

    public NoteEntity findById(Integer id);

    public Integer deleteById(Integer id);

    public Iterable<NoteEntity> findAll();


}