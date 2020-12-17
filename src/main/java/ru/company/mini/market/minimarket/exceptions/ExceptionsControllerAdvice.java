package ru.company.mini.market.minimarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
}
