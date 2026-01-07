package com.example.url_shortener.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UrlController {
	private final Map<String, String> store = new HashMap<>();

	@PostMapping("/shorten")
	public String shorten(@RequestParam String url) {
		String code = UUID.randomUUID().toString().substring(0, 6);
		store.put(code, url);
		return "http://localhost:8080/" + code;
	}

	@GetMapping("/{code}")
	public ResponseEntity<Void> redirect(@PathVariable String code) {
		String target = store.get(code);
		if (target == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(302).location(URI.create(target)).build();
	}
}
