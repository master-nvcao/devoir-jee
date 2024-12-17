package com.my.devoir.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/commandes")
    public ResponseEntity<String> commandesFallback() {
        return new ResponseEntity<>(
                "Commande service ne fonctionne pas mainteannt essayez plus tard.",
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/produits")
    public ResponseEntity<String> produitsFallback() {
        return new ResponseEntity<>(
                "Produit service ne fonctionne pas mainteannt essayez plus tard.",
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
