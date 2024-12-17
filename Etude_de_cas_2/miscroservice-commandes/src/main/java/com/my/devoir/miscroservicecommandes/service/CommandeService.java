package com.my.devoir.miscroservicecommandes.service;

import com.my.devoir.miscroservicecommandes.model.Commande;
import com.my.devoir.miscroservicecommandes.repository.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepo repository;

    @Value("${mes-config-ms.commandes-last}")
    private int commandesLast;

    public List<Commande> getAllCommandes() {
        return repository.findAll();
    }

    public List<Commande> getRecentCommandes() {
        LocalDate cutoffDate = LocalDate.now().minusDays(commandesLast);
        return repository.findAll().stream()
                .filter(commande -> !commande.getDate().isBefore(cutoffDate))
                .collect(Collectors.toList());
    }

    public Commande getCommandeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Commande createCommande(Commande commande) {
        return repository.save(commande);
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        return repository.findById(id).map(commande -> {
            commande.setDescription(updatedCommande.getDescription());
            commande.setQuantite(updatedCommande.getQuantite());
            commande.setDate(updatedCommande.getDate());
            commande.setMontant(updatedCommande.getMontant());
            commande.setIdProduit(updatedCommande.getIdProduit());
            return repository.save(commande);
        }).orElse(null);
    }

    public void deleteCommande(Long id) {
        repository.deleteById(id);
    }
}
