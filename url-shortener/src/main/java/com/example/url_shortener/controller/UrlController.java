package com.example.url_shortener.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class UrlController {
	private final Map<String, String> store = new HashMap<>();

	@PostConstruct
	public void init() {
		store.put("google", "https://www.google.com");
		store.put("youtube", "https://www.youtube.com");
		store.put("song1", "https://open.spotify.com/artist/4YRxDV8wJFPHPTeXepOstw");
	}

	@PostMapping("/shorten")
	public String shorten(@RequestParam String url) {
		String code = UUID.randomUUID().toString().substring(0, 6);
		store.put(code, url);
		return "http://65.2.73.56/" +code;
		//return "http://3.26.28.160:8080/" + code;
		//return "http://localhost:8080/" + code;
	}

	@GetMapping("/{code}")
	public ResponseEntity<Void> redirect(@PathVariable String code) {
		String target = store.get(code);
		if (target == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(302).location(URI.create(target)).build();
	}

	@GetMapping("/saiLove")
	public ResponseEntity<String> redirect() {
		return new ResponseEntity<String>("I love you Mayuri .. ", HttpStatus.OK);
	}
}
