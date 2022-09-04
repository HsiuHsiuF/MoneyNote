package com.example.MoneyNote.controller;

import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.service.impl.NoteServiceImpl;
import com.example.MoneyNote.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.MoneyNote.model.NoteEntity;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class NoteController {
    @Autowired
    NoteServiceImpl noteServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    //根據USER_ID尋找NOTE
    @GetMapping("/moneynotes")
    public String getNote(@SessionAttribute(value = "user") UserEntity userEntity, Model model, @RequestParam(value = "month") Integer month) {
        List<NoteEntity> moneyList = noteServiceImpl.getNoteByUserIdAndMonth(userEntity.getId(), month);
        model.addAttribute("moneyList", moneyList);
        NoteEntity noteEntity = new NoteEntity();
        model.addAttribute("moneyObject", noteEntity);

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
        return "redirect:/moneynotes";
    }
//    @ResponseBody
//    @PutMapping("/todos/{id}")
//    public void upadteTodo(@PathVariable Integer id, @RequestBody Todo todo) {
//        todoService.updateTodo(id ,todo);
//    }

    @ResponseBody
    @DeleteMapping("/moneynotes/{id}")
    public void deleteNote(@PathVariable Integer id) {
        noteServiceImpl.deleteNote(id);
    }

}
