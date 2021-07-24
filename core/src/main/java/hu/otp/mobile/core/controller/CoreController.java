package hu.otp.mobile.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
public class CoreController {

	private final Logger log = LoggerFactory.getLogger(CoreController.class);

	@GetMapping(path = "/validate-card")
	ResponseEntity<Boolean> validateCard(@RequestParam("user-token") String userToken, @RequestParam("card-id") int cardId,
			@RequestParam("payment") int payment) {

		log.info("Attempting card validation for user, userToken={}, cardId={}, payment={}", userToken, cardId, payment);

		return ResponseEntity.ok(true);
	}

	@GetMapping(path = "/validate-user")
	ResponseEntity<Boolean> validateUser(@RequestParam("user-token") String userToken) {

		log.info("Attempting user validation, userToken={}", userToken);

		return ResponseEntity.ok(true);
	}

}