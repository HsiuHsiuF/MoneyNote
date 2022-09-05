package com.example.MoneyNote.service;

import com.example.MoneyNote.model.NoteEntity;
import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.model.UserEntityCU;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    public List<NoteEntity> getNoteByUserIdAndDate(Integer id, Integer year, Integer month);

    public Integer createNote(NoteEntity noteEntity);

    public NoteEntity getNoteById(Integer id);

    public Boolean deleteNote(Integer id);

    public Iterable<NoteEntity> getNote();
}
