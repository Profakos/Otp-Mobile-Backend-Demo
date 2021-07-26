package hu.otp.mobile.api.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import opt.mobile.common.dto.ReservationErrorDto;
import opt.mobile.common.exceptions.EventException;
import opt.mobile.common.exceptions.MobileError;
import opt.mobile.common.exceptions.ReservationException;
import opt.mobile.common.exceptions.ValidationException;

@ControllerAdvice
public class ApiRestControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(ApiRestControllerAdvice.class);

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
		log.info("Client error, message={}", e);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getResponseBodyAsString());
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<String> handleHttpClientErrorException(HttpServerErrorException e) {
		log.info("Client error, message={}", e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getResponseBodyAsString());
	}

	@ExceptionHandler(EventException.class)
	public ResponseEntity<ReservationErrorDto> handleEventException(EventException e) {
		log.info("Unable to read event data, message={}", e.getMobileError().getLabel());

		ReservationErrorDto dto = new ReservationErrorDto();
		dto.setSuccess(false);
		dto.setErrorCode(e.getMobileError().getErrorCode());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
	}

	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<ReservationErrorDto> handleReservationException(ReservationException e) {
		log.info("Unable to create a valid reservation, message={}", e.getMobileError().getLabel());

		ReservationErrorDto dto = new ReservationErrorDto();
		dto.setSuccess(false);
		dto.setErrorCode(e.getMobileError().getErrorCode());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<MobileError> handleUserException(ValidationException e) {
		log.info("Unable to validate user message={}", e.getMobileError().getLabel());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMobileError());
	}
}
