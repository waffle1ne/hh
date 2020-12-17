package ru.company.mini.market.minimarket.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
