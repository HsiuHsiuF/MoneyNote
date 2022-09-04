package com.example.MoneyNote.controller;

import com.example.MoneyNote.model.UserEntity;
import com.example.MoneyNote.enums.HttpState;
import com.example.MoneyNote.service.impl.UserServiceImpl;
import com.example.MoneyNote.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/UserEntity")
public class MemberAccountRestController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<Boolean> checkUsernameDuplicate(@RequestParam(value = "username") String username) {
		
		UserEntity result = userServiceImpl.findByUserName(username);
		return new JsonResult<Boolean>(HttpState.SUCCESS.getState(), result != null ? true : false);
	}
	
}
