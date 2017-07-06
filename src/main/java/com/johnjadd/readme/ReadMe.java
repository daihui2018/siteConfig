package com.johnjadd.readme;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadMe {
	@RequestMapping("/ctg")
	public String sayHello() {
		return "Hello ctg";
	}
}
