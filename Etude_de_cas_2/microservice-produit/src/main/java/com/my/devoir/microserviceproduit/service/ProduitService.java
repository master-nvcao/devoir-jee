package com.my.devoir.microserviceproduit.service;

import com.my.devoir.microserviceproduit.model.Produit;
import com.my.devoir.microserviceproduit.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository repository;

    public List<Produit> getAllProduits() {
        return repository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return repository.findById(id);
    }

    public Produit createProduit(Produit produit) {
        return repository.save(produit);
    }

    public Produit updateProduit(Long id, Produit updatedProduit) {
        return repository.findById(id).map(produit -> {
            produit.setNom(updatedProduit.getNom());
            produit.setPrix(updatedProduit.getPrix());
            produit.setDescription(updatedProduit.getDescription());
            return repository.save(produit);
        }).orElseThrow(() -> new RuntimeException("Produit not found"));
    }

    public void deleteProduit(Long id) {
        repository.deleteById(id);
    }
}
