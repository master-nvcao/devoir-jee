package com.my.devoir.miscroservicecommandes.controller;

import com.my.devoir.miscroservicecommandes.model.Commande;
import com.my.devoir.miscroservicecommandes.service.CommandeService;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
@RequestMapping("/commandes")
public class CommandeController implements HealthIndicator {

    @Autowired
    private CommandeService service;
    @Autowired
    private CommandeService commandeService;

    @Retry(name = "commandeService", fallbackMethod = "fallbackListeDesCommandes")
    @TimeLimiter(name = "commandeService") // Use consistent configuration name
    @GetMapping()
    public CompletableFuture<List<Commande>> getAllCommandes() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000); // Simule un délai de 3 secondes
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return commandeService.getAllCommandes();
        });
    }

    public CompletableFuture<List<Commande>> fallbackListeDesCommandes(Throwable t) {
        System.out.println("Fallback triggered due to: " + t.getMessage());
        return CompletableFuture.completedFuture(List.of()); // Return an empty list as a fallback
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

    @Override
    public Health health() {
        return null;
    }
}
