package hu.otp.mobile.ticket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import opt.mobile.common.dto.ReservationErrorDto;
import opt.mobile.common.exceptions.EventException;
import opt.mobile.common.exceptions.ReservationException;
import opt.mobile.common.exceptions.ValidationException;

@ControllerAdvice
public class TicketRestControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(TicketRestControllerAdvice.class);

	@ExceptionHandler(EventException.class)
	public ResponseEntity<ReservationErrorDto> handleEventException(EventException e) {
		log.info("Unable to create a valid reservation, message={}", e.getMobileErrorMessage().getLabel());

		ReservationErrorDto dto = new ReservationErrorDto();
		dto.setSuccess(false);
		dto.setErrorCode(e.getMobileErrorMessage().getErrorCode());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
	}

	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<ReservationErrorDto> handleReservationException(ReservationException e) {
		log.info("Unable to create a valid reservation, message={}", e.getMobileErrorMessage().getLabel());

		ReservationErrorDto dto = new ReservationErrorDto();
		dto.setSuccess(false);
		dto.setErrorCode(e.getMobileErrorMessage().getErrorCode());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<String> handleUserException(ValidationException e) {
		log.info("Unable to validate user message={}", e.getMobileErrorMessage().getLabel());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMobileErrorMessage().getLabel());
	}

}
