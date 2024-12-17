package com.my.devoir.microserviceproduit.controller;

import com.my.devoir.microserviceproduit.model.Produit;
import com.my.devoir.microserviceproduit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService service;

    @GetMapping
    public List<Produit> getAllProduits() {
        return service.getAllProduits();
    }

    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable Long id) {
        return service.getProduitById(id).orElseThrow(() -> new RuntimeException("Produit not found"));
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return service.createProduit(produit);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return service.updateProduit(id, produit);
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        service.deleteProduit(id);
    }

}