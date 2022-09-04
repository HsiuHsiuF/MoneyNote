package com.example.MoneyNote.enums;

import lombok.Getter;

@Getter//列舉
public enum HttpState {
//1.2.3.4....
/*1*/	SUCCESS(2000, "成功"),
/*2*/	NOT_FOUND(4000, "請求失敗");
	
	private int state;
	private String description;

	HttpState(int state, String description) {
		this.state = state;
		this.description = description;
	}
	
}
