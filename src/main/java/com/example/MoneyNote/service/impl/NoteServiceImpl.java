package com.example.MoneyNote.service.impl;

import com.example.MoneyNote.model.UserEntity;
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
    public List<NoteEntity> getNoteByUserId(Integer id) {
        return noteDaoImpl.findByUser_id(id);
    }

    @Override
    public Integer createNote(NoteEntity noteEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = df.format(new Date());
        noteEntity.setDate(date);
        return noteDaoImpl.save(noteEntity);
    }


//    public NoteModel updateTodo(Integer id,NoteModel noteModel) {
//        try {
//            noteModel resTodo = findById(id);
//            Integer status = todo.getStatus();
//            resTodo.setStatus(status);
//            return todoDao.save(resTodo);
//        }catch (Exception exception) {
//            return null;
//        }

//     }

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
