package com.laptrinhjavaweb.dto;

import java.util.Date;

public class TransactionDTO extends AbstractDTO<TransactionDTO> {

	private String note;
	private String type;
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
