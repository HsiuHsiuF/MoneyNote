package com.example.MoneyNote.service.impl;

import com.example.MoneyNote.repository.impl.NoteDapImpl;
import com.example.MoneyNote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.MoneyNote.model.NoteEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteDapImpl noteDaoImpl;

    @Override
    public List<NoteEntity> getNoteByUserIdAndDate(Integer id, Integer year, Integer month) {
        return noteDaoImpl.findByUser_idAndDate(id, year, month);
    }

    @Override
    public Integer createNote(NoteEntity noteEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = df.format(new Date());
        noteEntity.setDate(date);
        return noteDaoImpl.save(noteEntity);
    }
    @Override
    public NoteEntity getNoteById(Integer id) {
        NoteEntity noteEntity = noteDaoImpl.findById(id);
        return noteEntity;
    }

    @Override
    public Boolean deleteNote(Integer id) {
        try {
            NoteEntity noteEntity = getNoteById(id);
            noteDaoImpl.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Iterable<NoteEntity> getNote() { return noteDaoImpl.findAll(); }
}
