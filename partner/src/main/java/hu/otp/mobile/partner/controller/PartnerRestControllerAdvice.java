package hu.otp.mobile.partner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import opt.mobile.common.dto.ReservationErrorDto;
import opt.mobile.common.exceptions.ReservationException;

@ControllerAdvice
public class PartnerRestControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(PartnerRestControllerAdvice.class);

	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<ReservationErrorDto> handleReservationException(ReservationException e) {
		log.info("Unable to create a valid reservation");

		ReservationErrorDto dto = new ReservationErrorDto();
		dto.setSuccess(false);
		dto.setErrorCode(e.getMobileErrorMessage().getErrorCode());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
	}
}
