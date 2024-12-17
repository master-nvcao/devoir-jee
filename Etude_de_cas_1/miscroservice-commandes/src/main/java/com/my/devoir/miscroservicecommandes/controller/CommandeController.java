package com.my.devoir.miscroservicecommandes.controller;

import com.my.devoir.miscroservicecommandes.model.Commande;
import com.my.devoir.miscroservicecommandes.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService service;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return service.getAllCommandes();
    }

    @GetMapping("/recent")
    public List<Commande> getRecentCommandes() {
        return service.getRecentCommandes();
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return service.getCommandeById(id);
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return service.createCommande(commande);
    }

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        return service.updateCommande(id, commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        service.deleteCommande(id);
    }
}
