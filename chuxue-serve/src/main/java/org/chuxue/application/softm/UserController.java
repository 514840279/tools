package org.chuxue.application.softm;

import java.security.Principal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PostMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}