package com.commandes.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commandes.dao.CommandesDao;
import com.commandes.model.Commande;

import java.util.List;
import java.util.Optional;
@Service
public class CommandeService {

	private final CommandesDao commandeRepository;
	@Autowired
	

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public CommandeService(CommandesDao commandeRepository) {
		super();
		this.commandeRepository = commandeRepository;
	}

	public Optional<Commande> getCommandeById(int id) {
        return commandeRepository.findById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(int id, Commande updatedCommande) {
        if (commandeRepository.existsById(id)) {
        	updatedCommande.setId(id);
            return commandeRepository.save(updatedCommande);
        }
        return null; 
    }

    public void deleteCommande(int id) {
    	commandeRepository.deleteById(id);
    }
	
	
}
   