package com.commandes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commandes.model.Commande;

public interface CommandesDao extends JpaRepository<Commande, Integer> {
	
}
 


