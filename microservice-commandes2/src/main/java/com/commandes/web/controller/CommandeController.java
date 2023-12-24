package com.commandes.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commandes.configurations.ApplicationPropertiesConfiguration;
import com.commandes.dao.CommandesDao;
import com.commandes.model.Commande;
import com.commandes.web.exceptions.CommandeNotFoundException;
import com.commandes.web.exceptions.ImpossibleAjouterCommandeException;
@RestController
public class CommandeController  implements HealthIndicator  {

	@Autowired
	CommandesDao commandeDao;

	@Autowired
	ApplicationPropertiesConfiguration appProperties;
	
	  @PostMapping (value ="/commandes")
	    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

	        Commande nouvelleCommande = commandeDao.save(commande);

	        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

	        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
	    }


	// Affiche la liste de tous les commandes disponibles
	@GetMapping(value = "/commandes")
	public List<Commande> listeDesCommandes() {
		
		System.out.println(" *** CommandeController listeDesCommandes() ");
		List<Commande> commandes = commandeDao.findAll();

		if (commandes.isEmpty())
			throw new CommandeNotFoundException("Aucune commande n'est disponible");

		List<Commande> listeLimitee = commandes.subList(0, appProperties.getCommandeslast());

		return listeLimitee;

	}

	// Récuperer une commande par son id
	@GetMapping(value = "/commandes/{id}")
	public Optional<Commande> recupererUneCommande(@PathVariable int id) {
		System.out.println(" *** CommandeController recupererUneCommande(@PathVariable int id) ");
		
		Optional<Commande> commande = commandeDao.findById(id);

		if (!commande.isPresent())
			throw new CommandeNotFoundException("La commande correspondant à l'id " + id + " n'existe pas");

		return commande;
	}
	@PutMapping("/commandes/{id}")
	public ResponseEntity<Void> updateCommande(@PathVariable int id, @RequestBody Commande commande) {
	    Optional<Commande> existingCommandeOptional = commandeDao.findById(id);

	    if (existingCommandeOptional.isPresent()) {
	        Commande existingCommande = existingCommandeOptional.get();

	        // Mettez à jour uniquement les champs non nuls de la nouvelle commande
	        if (commande.getDescription() != null) {
	            existingCommande.setDescription(commande.getDescription());
	        }
	        if (commande.getDate_commande() != null) {
	            existingCommande.setDate_commande(commande.getDate_commande());
	        }
	        if (commande.getQuantite() != null) {
	            existingCommande.setQuantite(commande.getQuantite());
	        }
	        if (commande.getMontant() != 0.0) {
	            existingCommande.setMontant(commande.getMontant());
	        }
	        if (commande.getId_produit() != 0) {
	            existingCommande.setId_produit(commande.getId_produit());
	        }
	        

	        // Enregistrez la commande mise à jour
	        commandeDao.save(existingCommande);

	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    throw new CommandeNotFoundException("La commande correspondant à l'id " + id + " n'existe pas");
	}
	@DeleteMapping(value = "/commandes/{id}")
	 public ResponseEntity<Void> deleteCommande(@PathVariable int id) {
	     Optional<Commande> existingCommandeOptional = commandeDao.findById(id);

	     if (existingCommandeOptional.isPresent()) {
	         commandeDao.deleteById(id);
	         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	     }

	     throw new CommandeNotFoundException("La commande correspondant à l'id " + id + " n'existe pas");
	 }

	@Override
	public Health health() {
		
		System.out.println(" ***Actuator : CommandeController health() ");
		
		List<Commande> commandes = commandeDao.findAll();
		if (commandes.isEmpty()) {
			return Health.down().build();
		}
		return Health.up().build();
	}
}

		
	