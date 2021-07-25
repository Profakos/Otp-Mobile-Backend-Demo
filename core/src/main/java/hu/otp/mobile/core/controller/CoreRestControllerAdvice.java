package hu.otp.mobile.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import opt.mobile.common.exceptions.MobileErrorMessage;
import opt.mobile.common.exceptions.ValidationException;

@ControllerAdvice
public class CoreRestControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(CoreRestControllerAdvice.class);

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<MobileErrorMessage> handleUserException(ValidationException e) {
		log.info("Unable to validate user message={}", e.getMobileErrorMessage().getLabel());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMobileErrorMessage());
	}
}
