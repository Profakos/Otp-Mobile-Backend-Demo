package hu.otp.mobile.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import opt.mobile.common.exceptions.MobileBackendException;

@ControllerAdvice
public class ApiRestControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(ApiRestControllerAdvice.class);

	@ExceptionHandler(MobileBackendException.class)
	public ResponseEntity<String> handleMobileBackendException(MobileBackendException e) {
		log.info("Hiba elkapva.");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMobileErrorMessage().getLabel());
	}
}
