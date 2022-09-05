package com.example.MoneyNote.controller;

import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.model.UserEntityCU;
import com.example.MoneyNote.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    //登入頁面
    @GetMapping("/login")
    public String login(@ModelAttribute UserEntity userEntity , @ModelAttribute(value = "MESSAGE") String message){
        return "login";
    }

    //登入
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserEntity userEntity, HttpSession session, RedirectAttributes redirectAttributes) {
        UserEntity user = userServiceImpl.login(userEntity);
        if(user == null) {
            redirectAttributes.addFlashAttribute("MESSAGE", "帳號或密碼錯誤");
            return "redirect:login";
        }
        session.setAttribute("user", user);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) +1;
        return "redirect:/moneynotes?year="+year+"&month="+month;
    }

    //註冊頁面
    @GetMapping("/signup")
    public String signup(@ModelAttribute UserEntityCU userEntityCU) {

        return "signup";
    }

    //註冊
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute UserEntityCU userEntityCU, RedirectAttributes redirectAttributes) {
        Optional<String> user = userServiceImpl.createUser(userEntityCU);
        String message = user.orElse("註冊成功");
        redirectAttributes.addFlashAttribute("MESSAGE", message);
        return "redirect:/login";
    }

    //修改密碼頁面
    @GetMapping("/update")
    public String update(@ModelAttribute UserEntityCU userEntityCU) {

        return "updatePassword";
    }

    //修改密碼
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute UserEntityCU userEntityCU, RedirectAttributes redirectAttributes, @SessionAttribute(value = "user") UserEntity userEntity){
        userEntityCU.setUsername(userEntity.getUsername());
        Optional<String> user = userServiceImpl.updatePassword(userEntityCU);
        String message = user.orElse("修改成功");
        redirectAttributes.addFlashAttribute("MESSAGE", message);
        return "redirect:/login";
    }


    //用戶登出
    @GetMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        if(session.getAttribute("user") != null){
            session.removeAttribute("user");
            sessionStatus.setComplete();
        }
        return "redirect:/login";
    }
}
