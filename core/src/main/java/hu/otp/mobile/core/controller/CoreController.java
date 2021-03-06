package hu.otp.mobile.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.otp.mobile.core.service.ValidationService;
import opt.mobile.common.dto.ValidationDto;

@RestController
@RequestMapping("/core")
public class CoreController {

	private final Logger log = LoggerFactory.getLogger(CoreController.class);

	@Autowired
	ValidationService validationService;

	@GetMapping(path = "/validate-card")
	ResponseEntity<ValidationDto> validateCard(@RequestParam("user-token") String userToken, @RequestParam("card-id") int cardId,
			@RequestParam("payment") int payment) {

		log.info("Received card validation request, userToken={}, cardId={}, payment={}", userToken, cardId, payment);

		return ResponseEntity.ok(validationService.validateCard(userToken, cardId, payment));
	}

	@GetMapping(path = "/validate-user")
	ResponseEntity<ValidationDto> validateUser(@RequestParam("user-token") String userToken) {

		log.info("Received user validation request, userToken={}", userToken);

		return ResponseEntity.ok(validationService.validateUser(userToken));
	}

}
