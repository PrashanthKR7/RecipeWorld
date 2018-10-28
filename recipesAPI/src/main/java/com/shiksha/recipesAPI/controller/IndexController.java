package com.shiksha.recipesAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IndexController {

	@GetMapping
	public String sayHello() {
		return "Hello and welcome to Recipes world";
	}
}
