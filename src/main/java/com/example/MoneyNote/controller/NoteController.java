package com.example.MoneyNote.controller;

import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.service.impl.NoteServiceImpl;
import com.example.MoneyNote.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.MoneyNote.model.NoteEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
public class NoteController {
    @Autowired
    NoteServiceImpl noteServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    //根據USER_ID尋找NOTE
    @GetMapping("/moneynotes")
    public String getNote(@SessionAttribute(value = "user") UserEntity userEntity, Model model, @RequestParam(value = "year") Integer year, @RequestParam(value = "month") Integer month) {
        List<NoteEntity> moneyList = noteServiceImpl.getNoteByUserIdAndDate(userEntity.getId(), year, month);
        model.addAttribute("moneyList", moneyList);
        NoteEntity noteEntity = new NoteEntity();
        model.addAttribute("moneyObject", noteEntity);
        String name = userEntity.getName();
        model.addAttribute("name", name);
        return "moneyList";
    }

    //尋找全部的NOTE
//    @GetMapping("/moneynotes")
//    public String getNote(Model model) {
//        Iterable<NoteEntity>  moneyList = noteServiceImpl.getNote();
//        model.addAttribute("moneyList", moneyList);
//        NoteEntity noteEntity = new NoteEntity();
//        model.addAttribute("moneyObject", noteEntity);
//        return "moneyList";
//    }

    //儲存NOTE
    @PostMapping("/moneynotes")
    public String createNote(@ModelAttribute NoteEntity note, @SessionAttribute(value = "user") UserEntity userEntity) {
        note.setUser_id(userEntity.getId());
        noteServiceImpl.createNote(note);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) +1;
        return "redirect:/moneynotes?year="+year+"&month="+month;
    }

    @ResponseBody
    @DeleteMapping("/moneynotes/{id}")
    public void deleteNote(@PathVariable Integer id) {
        noteServiceImpl.deleteNote(id);
    }

}
