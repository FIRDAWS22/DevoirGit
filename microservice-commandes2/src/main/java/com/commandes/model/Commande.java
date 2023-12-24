package com.commandes.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Commande {
	@Id
    @GeneratedValue
    private int id;

    private Integer quantite;

    private Date date_commande;

    private String description;

    private float  montant;
    
    private int id_produit;
    

    public Commande() {
    }


	public Commande(int id, Integer quantite, Date date_commande, String description, float montant) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.date_commande = date_commande;
		this.description = description;
		this.montant = montant;
		this.id_produit = id_produit;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getQuantite() {
		return quantite;
	}


	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}


	public Date getDate_commande() {
		return date_commande;
	}


	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	public int getId_produit() {
		return id;
	}


	public void setId_produit(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Commande [id=" + id + ", quantite=" + quantite + ", date_commande=" + date_commande + ", description="
				+ description + ", montant=" + montant + ", id_produit=" + id_produit + "]";
	}


	
    
}
