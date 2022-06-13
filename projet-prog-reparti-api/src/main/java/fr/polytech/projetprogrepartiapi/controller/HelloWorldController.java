package fr.polytech.projetprogrepartiapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {
	private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	/**
	 * @return ResponseEntity Hello World
	 */
	@GetMapping
	public ResponseEntity<String> helloWorldTest() {
		logger.info("GET helloWorldTest");
		return ResponseEntity.ok("\"Hello World !\"");
	}
}
