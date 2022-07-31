package com.laptrinhjavaweb.exception;

public class FieldNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FieldNotFoundException (String str)
	{
		super(str);
	}
}
