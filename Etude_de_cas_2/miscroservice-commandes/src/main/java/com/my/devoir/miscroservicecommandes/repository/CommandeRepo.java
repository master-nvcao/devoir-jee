package com.my.devoir.miscroservicecommandes.repository;

import com.my.devoir.miscroservicecommandes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Long> {

}
